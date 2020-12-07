package ru.novikov.webapp.dao;

import org.springframework.stereotype.Component;
import ru.novikov.webapp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom Adamson", 24, "tom@mail.ru", "Pilot"));
        people.add(new Person(++PEOPLE_COUNT, "Bob Charle", 52, "bob@mail.ru", "Pilot"));
        people.add(new Person(++PEOPLE_COUNT, "Mike Biker", 18, "mike@yahoo.com", "Flight engineer"));
        people.add(new Person(++PEOPLE_COUNT, "Katy Perdon", 34, "katy@gmail.com", "Stewardess"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setVocation(updatedPerson.getVocation());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}