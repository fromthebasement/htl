package com.fromthebasement.dao;

import com.fromthebasement.model.Survey;
import com.fromthebasement.model.Tattoo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;

/**
 * Created by jeffginn on 4/13/14.
 */
public class SurveyDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyDao surveyDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSurvey() throws Exception {
        Survey survey = new Survey();
        survey.setName( "Tiger's Opening Day" );

        Tattoo tattoo = new Tattoo();
        tattoo.setImageUrl( "/images/tigers-logo.png" );
        tattoo.setSlogan( "Best opening day survey ever" );

        survey.setTattoo( tattoo );

        DateTime dateTime = new DateTime();

        survey.setEndTime( dateTime );

        survey = surveyDao.save(survey);

        survey = surveyDao.get(survey.getId());

        assertEquals("Tiger's Opening Day", survey.getName());
        assertEquals("Best opening day survey ever", survey.getTattoo().getSlogan());
        assertEquals(dateTime, survey.getEndTime());
        assertNotNull(survey.getId());

        log.debug("removing survey...");

        surveyDao.remove(survey.getId());

        // should throw DataAccessException
        surveyDao.get(survey.getId());
    }
}
