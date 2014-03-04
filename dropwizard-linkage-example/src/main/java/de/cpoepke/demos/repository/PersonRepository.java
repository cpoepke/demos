package de.cpoepke.demos.repository;

import com.google.common.collect.Lists;
import de.cpoepke.demos.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonRepository {

    private static PersonRepository instance = new PersonRepository();

    private PersonRepository() {
    }

    public static PersonRepository getInstance() {
        return instance;
    }

    private List<Person> entries = Collections.synchronizedList(Lists.newArrayList());

    public Person get(UUID id) {
        Optional<Person> person = find(id);
        if (person.isPresent()) {
            return person.get();
        }
        return null;
    }

    public Person put(Person person) {
        entries.add(person);
        return person;
    }

    public boolean delete(UUID id) {
        Optional<Person> person = find(id);
        if (person.isPresent()) {
            entries.remove(person.get());
            return true;
        }
        return false;
    }

    public long size() {
        return entries.size();
    }

    private Optional<Person> find(UUID id) {
        return entries.stream().filter(element -> element.getId().equals(id)).findFirst();
    }
}
