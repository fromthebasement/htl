package com.fromthebasement.service.impl;

import com.fromthebasement.dao.PersonDao;
import com.fromthebasement.model.Person;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

/**
 * Created by jeffginn on 4/5/14.
 */
public class PersonManagerImplTest extends BaseManagerMockTestCase {
    @InjectMocks
    private PersonManagerImpl manager;

    @Mock
    private PersonDao dao;

    @Test
    public void testGetPerson() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Person person = new Person();
        given(dao.get(id)).willReturn(person);
        //when
        Person result = manager.get(id);
        //then
        assertSame(person, result);
    }

    @Test
    public void testGetPersons() {
        log.debug("testing getAll...");
        //given
        final List persons = new ArrayList();
        given(dao.getAll()).willReturn(persons);
        //when
        List result = manager.getAll();
        //then
        assertSame(persons, result);
    }

    @Test
    public void testSavePerson() {
        log.debug("testing save...");
        //given
        final Person person = new Person();
        // enter all required fields

        given(dao.save(person)).willReturn(person);
        //when
        manager.save(person);
        //then
        verify(dao).save(person);
    }

    @Test
    public void testRemovePerson() {
        log.debug("testing remove...");
        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);
        //when
        manager.remove(id);
        //then
        verify(dao).remove(id);
    }

    @Test
    public void testFindByLastName() {
        log.debug("testing find by last name...");
        //given
        final String lastName = "Skywalker";
        final List persons = new ArrayList();
        given(dao.findByLastName(lastName)).willReturn(persons);
        //when
        List result = manager.findByLastName(lastName);
        //then
        assertSame(persons, result);
    }
}
