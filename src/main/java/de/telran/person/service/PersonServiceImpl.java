package de.telran.person.service;

import de.telran.person.entity.Person;
import de.telran.person.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person getById(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
    }

    @Override
    public void save(Person person) {
        if (person != null) {
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException("Person cannot be null");
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (id != null) {
            personRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    @Override
    public void update(Integer id, Person person) {
        Optional<Person> persistPersonOptional = personRepository.findById(id);

        persistPersonOptional.ifPresent(persistPerson -> {
            if (person.getName() != null) {
                persistPerson.setName(person.getName());
            }

            if (person.getStatus() != null) {
                persistPerson.setStatus(person.getStatus());
            }

            int defaultRating = -1;
            persistPerson.setRating(person.getRating() != defaultRating ? person.getRating() : defaultRating);

            personRepository.save(persistPerson);
        });
    }

    @Override
    public List<Person> findPeopleByRatingRange(int minRating, int maxRating) {
        return personRepository.findByRatingBetween(minRating, maxRating);
    }

    @Override
    public long countPeopleByDescriptionContains(String keyword) {
        return personRepository.countByDescriptionContainingIgnoreCase(keyword);
    }

    @Override
    public boolean doesPersonExistById(Integer id) {
        return personRepository.existsById(id);
    }


   @Override
   public Page<Person> getAllPeopleWithPagination(Pageable pageable) {
        return personRepository.findAll(pageable);
   }

}