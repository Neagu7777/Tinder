package de.telran.tinder.controller;

import de.telran.tinder.entity.Person;
import de.telran.tinder.service.TinderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableScheduling
public class TinderController {

    private final TinderService tinderService;

    @GetMapping(value = "/person/{id}")
    public Person getPersonById(@PathVariable(value = "id") Integer id) {
        return tinderService.getById(id);
    }

    @PostMapping(value = "/person")
    public void savePerson(@RequestBody Person person) {
        tinderService.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        tinderService.deleteById(id);
    }

    @PutMapping(value = "/person/{id}")
    public void updatePersonById(@PathVariable(value = "id") Integer id, @RequestBody Person person) {
        tinderService.update(id, person);
    }

    @GetMapping(value = "/person/rating")
    public List<Person> findPeopleByRatingRange(
            @RequestParam(value = "minRating") int minRating,
            @RequestParam(value = "maxRating") int maxRating) {
        return tinderService.findPeopleByRatingRange(minRating, maxRating);
    }

    @GetMapping(value = "/person/count")
    public long countPeopleByDescriptionContains(@RequestParam(value = "keyword") String keyword) {
        return tinderService.countPeopleByDescriptionContains(keyword);
    }

    @GetMapping(value = "/person/exists")
    public boolean doesPersonExistById(@RequestParam(value = "id") Integer id) {
        return tinderService.doesPersonExistById(id);
    }

    @GetMapping(value = "/person/all")
    public Page<Person> getAllPeopleWithPagination(Pageable pageable) {
        return tinderService.getAllPeopleWithPagination(pageable);
    }

    @Scheduled(cron = "0 55 13 * * *") // каждый день в 13:55
    public void addPointsToRandomUser() {
        List<Person> allUsers = tinderService.getAllUsers();
        Person randomUser = tinderService.getRandomUser(allUsers);
        if (randomUser != null) {
            tinderService.addPointsToUser(randomUser.getId(), 100);
        }
    }

    @Scheduled(cron = "0 50 23 * * *") // каждый день в 23:50
    public void subtractPointsFromRandomUser() {
        List<Person> allUsers = tinderService.getAllUsers();
        Person randomUser = tinderService.getRandomUser(allUsers);
        if (randomUser != null) {
            tinderService.subtractPointsFromUser(randomUser.getId(), 100);
        }
    }
}