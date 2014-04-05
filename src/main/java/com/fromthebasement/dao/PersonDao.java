package com.fromthebasement.dao;

import com.fromthebasement.model.Person;

import java.util.List;

/**
 * Created by jeffginn on 4/5/14.
 */
public interface PersonDao extends GenericDao<Person, Long> {
    public List<Person> findByLastName(String lastName);
}
