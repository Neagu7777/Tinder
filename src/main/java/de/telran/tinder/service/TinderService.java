package de.telran.tinder.service;

import de.telran.tinder.entity.Person;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
@RestController
public interface TinderService {
    int minRating = 1;
    int maxRating = 10;

    Person getById(Integer id);

    void save(Person person);

    void deleteById(Integer id);

    void update(Integer id, Person person);

    List<Person> findPeopleByRatingRange(int minRating, int maxRating);

    long countPeopleByDescriptionContains(String keyword);

    boolean doesPersonExistById(Integer id);

    Page<Person> getAllPeopleWithPagination(Pageable pageable);
    void addPointsToUser(Integer userId, int points);

    void subtractPointsFromUser(Integer userId, int points);

    List<Person> getAllUsers();

    Person getRandomUser(List<Person> allUsers);
}
