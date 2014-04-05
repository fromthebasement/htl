package com.fromthebasement.dao;

import com.fromthebasement.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jeffginn on 4/5/14.
 */
public class PersonDaoTest extends BaseDaoTestCase {
    @Autowired
    private PersonDao personDao;

    @Test
    public void testFindPersonByLastName() throws Exception {
        List<Person> people = personDao.findByLastName("Raible");
        assertTrue(people.size() > 0);
    }

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Elway");

        person = personDao.save(person);

        person = personDao.get(person.getId());

        assertEquals("John", person.getFirstName());
        assertNotNull(person.getId());

        log.debug("removing person...");

        personDao.remove(person.getId());

        // should throw DataAccessException
        personDao.get(person.getId());
    }
}
