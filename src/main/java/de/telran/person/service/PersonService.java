package de.telran.person.service;

import de.telran.person.entity.Person;

public interface PersonService {

    Person getById(Integer id);

    void save(Person person);

    void deleteById(Integer id);

    void update(Integer id, Person person);



}
