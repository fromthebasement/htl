package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.PersonDao;
import com.fromthebasement.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by jeffginn on 4/5/14.
 */
@Repository("personDao")
public class PersonDaoJpa extends GenericDaoJpa<Person, Long> implements PersonDao {

    public PersonDaoJpa() {
        super(Person.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> findByLastName(String lastName) {
        Query q = getEntityManager().createQuery("select p from Person p where p.lastName=?");
        q.setParameter(1, lastName);
        return q.getResultList();
    }
}
