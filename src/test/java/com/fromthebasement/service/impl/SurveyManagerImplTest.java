package com.fromthebasement.service.impl;

import com.fromthebasement.dao.PersonDao;
import com.fromthebasement.dao.SurveyDao;
import com.fromthebasement.model.Person;
import com.fromthebasement.model.Survey;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.*;

/**
 * Created by jeffginn on 4/14/14.
 */
public class SurveyManagerImplTest extends BaseManagerMockTestCase {
    @InjectMocks
    private SurveyManagerImpl manager;

    @Mock
    private SurveyDao dao;

    @Test
    public void testGetSurvey() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Survey survey = new Survey();
        given(dao.get(id)).willReturn(survey);
        //when
        Survey result = manager.get(id);
        //then
        assertSame(survey, result);
    }

    @Test
    public void testGetSurveys() {
        log.debug("testing getAll...");
        //given
        final List surveys = new ArrayList();
        given(dao.getAll()).willReturn(surveys);
        //when
        List result = manager.getAll();
        //then
        assertSame(surveys, result);
    }

    @Test
    public void testSaveSurvey() {
        log.debug("testing save...");
        //given
        final Survey survey = new Survey();
        // enter all required fields

        given(dao.save(survey)).willReturn(survey);
        //when
        manager.save(survey);
        //then
        verify(dao).save(survey);
    }

    @Test
    public void testRemoveSurvey() {
        log.debug("testing remove...");
        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);
        //when
        manager.remove(id);
        //then
        verify(dao).remove(id);
    }
}
