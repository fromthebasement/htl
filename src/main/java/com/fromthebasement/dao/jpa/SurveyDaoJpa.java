package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.SurveyDao;
import com.fromthebasement.dao.jpa.GenericDaoJpa;
import com.fromthebasement.model.Person;
import com.fromthebasement.model.Survey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jeffginn on 4/13/14.
 */
@Repository("surveyDao")
public class SurveyDaoJpa extends GenericDaoJpa<Survey, Long> implements SurveyDao {
    public SurveyDaoJpa() {
        super(Survey.class);
    }
}
