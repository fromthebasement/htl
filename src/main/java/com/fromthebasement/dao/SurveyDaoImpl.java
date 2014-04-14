package com.fromthebasement.dao;

import com.fromthebasement.dao.jpa.GenericDaoJpa;
import com.fromthebasement.model.Person;
import com.fromthebasement.model.Survey;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 4/13/14.
 */
@Repository("surveyDao")
public class SurveyDaoImpl extends GenericDaoJpa<Survey, Long> implements SurveyDao {
    public SurveyDaoImpl() {
        super(Survey.class);
    }
}
