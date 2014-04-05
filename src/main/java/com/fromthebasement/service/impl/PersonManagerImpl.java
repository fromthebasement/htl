package com.fromthebasement.service.impl;

import com.fromthebasement.dao.PersonDao;
import com.fromthebasement.model.Person;
import com.fromthebasement.service.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffginn on 4/5/14.
 */
@Service("personManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {
    PersonDao personDao;

    @Autowired
    public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }

    public List<Person> findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }

    public List<Person> getPeople() {
        return personDao.getAll();
    }
}
