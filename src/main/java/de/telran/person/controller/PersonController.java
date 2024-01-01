package de.telran.person.controller;

import de.telran.person.service.PersonService;
import de.telran.person.entity.Person;
import de.telran.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}