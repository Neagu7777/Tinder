package de.telran.tinder.repository;

import de.telran.tinder.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TinderRepository extends JpaRepository<Person, Integer> {

    List<Person> findByRatingBetween(int minRating, int maxRating);

    long countByDescriptionContainingIgnoreCase(String keyword);

    boolean existsById(Integer id);
}
