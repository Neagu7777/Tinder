package de.telran.person.repository;

import de.telran.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByRatingBetween(int minRating, int maxRating);

    long countByDescriptionContainingIgnoreCase(String keyword);

    boolean existsById(Integer id);
}
