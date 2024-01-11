package de.telran.tinder.service;

import de.telran.tinder.entity.Person;
import de.telran.tinder.repository.TinderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TinderServiceImpl implements TinderService {

    private final TinderRepository tinderRepository;

    @Override
    public Person getById(Integer id) {
        return tinderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
    }

    @Override
    public void save(Person person) {
        if (person != null) {
            tinderRepository.save(person);
        } else {
            throw new IllegalArgumentException("Person cannot be null");
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (id != null) {
            tinderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    @Override
    public void update(Integer id, Person person) {
        Optional<Person> persistPersonOptional = tinderRepository.findById(id);

        persistPersonOptional.ifPresent(persistPerson -> {
            if (person.getName() != null) {
                persistPerson.setName(person.getName());
            }

            if (person.getStatus() != null) {
                persistPerson.setStatus(person.getStatus());
            }

            int defaultRating = -1;
            persistPerson.setRating(person.getRating() != defaultRating ? person.getRating() : defaultRating);

            tinderRepository.save(persistPerson);
        });
    }

    @Override
    public List<Person> findPeopleByRatingRange(int minRating, int maxRating) {
        return tinderRepository.findByRatingBetween(minRating, maxRating);
    }

    @Override
    public long countPeopleByDescriptionContains(String keyword) {
        return tinderRepository.countByDescriptionContainingIgnoreCase(keyword);
    }

    @Override
    public boolean doesPersonExistById(Integer id) {
        return tinderRepository.existsById(id);
    }


   @Override
   public Page<Person> getAllPeopleWithPagination(Pageable pageable) {
        return tinderRepository.findAll(pageable);
   }

    @Override
    public void addPointsToUser(Integer userId, int points) {
        Person user = getById(userId);
        user.setRating(user.getRating() + points);
        save(user);
    }

    @Override
    public void subtractPointsFromUser(Integer userId, int points) {
        Person user = getById(userId);
        user.setRating(user.getRating() - points);
        save(user);
    }

    @Override
    public List<Person> getAllUsers() {
        return null;
    }

    @Override
    public Person getRandomUser(List<Person> allUsers) {
        return null;
    }

}