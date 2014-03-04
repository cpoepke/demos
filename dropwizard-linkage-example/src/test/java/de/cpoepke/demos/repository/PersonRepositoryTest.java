package de.cpoepke.demos.repository;

import de.cpoepke.demos.model.Person;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PersonRepositoryTest {

    private PersonRepository repo = PersonRepository.getInstance();

    @Test
    public void testPut() {
        testAndAssertPut();
    }

    @Test
    public void testGet() {
        UUID id = testAndAssertPut();

        Person person = repo.get(id);
        assertEquals(id, person.getId());
        assertEquals("Max Mustermann", person.getName());
    }

    @Test
    public void testGetNotFound() {
        assertNull(repo.get(UUID.randomUUID()));
    }

    @Test
    public void testDelete() {
        UUID id = testAndAssertPut();

        long size = repo.size();
        repo.delete(id);
        assertEquals(size - 1, repo.size());
        assertNull(repo.get(id));
    }

    private UUID testAndAssertPut() {
        Person person = new Person();
        person.setName("Max Mustermann");
        Person savedPerson = repo.put(person);
        assertNotNull(savedPerson);
        assertNotNull(savedPerson.getId());
        assertEquals("Max Mustermann", savedPerson.getName());
        return person.getId();
    }
}
