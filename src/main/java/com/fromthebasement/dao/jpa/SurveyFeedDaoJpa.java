package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.SurveyFeedDao;
import com.fromthebasement.model.Person;
import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by jeffginn on 5/14/14.
 */
@Repository("surveyFeedDao")
public class SurveyFeedDaoJpa extends GenericDaoJpa<SurveyFeed, Long> implements SurveyFeedDao {
    public SurveyFeedDaoJpa() {
        super(SurveyFeed.class);
    }
}
