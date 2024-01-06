package de.telran.person.controller;

import de.telran.person.service.PersonService;
import de.telran.person.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/person/{id}")
    public Person getPersonById(@PathVariable(value = "id") Integer id) {
        Person person = personService.getById(id);
        return person;
    }

    @PostMapping(value = "/person")
    public void savePerson(@RequestBody Person person) {
        personService.save(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        personService.deleteById(id);
    }

    @PutMapping(value = "/person/{id}")
    public void updatePersonrById(@PathVariable(value = "id") Integer id, @RequestBody Person person) {
        personService.update(id, person);
    }

    @GetMapping(value = "/person/rating")
    public List<Person> findPeopleByRatingRange(
            @RequestParam(value = "minRating") int minRating,
            @RequestParam(value = "maxRating") int maxRating) {
        return personService.findPeopleByRatingRange(minRating, maxRating);
    }

    @GetMapping(value = "/person/count")
    public long countPeopleByDescriptionContains(@RequestParam(value = "keyword") String keyword) {
        return personService.countPeopleByDescriptionContains(keyword);
    }

    @GetMapping(value = "/person/exists")
    public boolean doesPersonExistById(@RequestParam(value = "id") Integer id) {
        return personService.doesPersonExistById(id);
    }
    @GetMapping(value = "/person/all")
    public Page<Person> getAllPeopleWithPagination(Pageable pageable) {
        return personService.getAllPeopleWithPagination(pageable);
    }

}