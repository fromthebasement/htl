package com.fromthebasement.dao;

import com.fromthebasement.model.Survey;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.model.Tattoo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jeffginn on 7/26/14.
 */
public class SurveyResponseDaoTest extends BaseDaoTestCase {
    @Autowired
    private SurveyResponseDao surveyResponseDao;

    @Test
    public void testGetAllActive() throws Exception {
        List<SurveyResponse> surveyResponses = surveyResponseDao.getAllActive(-2L);
        assertNotNull(surveyResponses);
        assertEquals(0,surveyResponses.size());
    }

    @Test
    public void testGet() throws Exception {
        SurveyResponse surveyResponse = surveyResponseDao.get(1, 1);

        assertNotNull(surveyResponse);

        if( surveyResponse.getId() == null ) {
            surveyResponseDao.save(surveyResponse);
            surveyResponse = surveyResponseDao.get(1,1);
        }

        assertNotNull(surveyResponse);
        assertNotNull(surveyResponse.getId());
    }
}
