
package de.telran.person.service;

import de.telran.person.entity.Person;
import de.telran.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person getById(Integer id) {
        Optional<Person> optional = personRepository.findById(id);

        return optional.orElse(null);
    }

    @Override
    public void save(Person person) {
        if (person == null) {
            return;
        }

        personRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            return;
        }

        personRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Person person) {
        if (person == null) {
            return;
        }

        Optional<Person> persistPersonOptional = personRepository.findById(id); //находим человека

        if (persistPersonOptional.isPresent()) { //если есть
            Person persistPerson = persistPersonOptional.get();
            persistPerson.setName(person.getName()); //в старое имя устанавливаем новое имя
            persistPerson.setStatus(person.getStatus()); // Изменение статуса
            personRepository.save(persistPerson); //пересохраняем
        }
    }

    @Override
    public void savePersonChange() {

    }

    @Override
    public void savePerson() {

    }
}

