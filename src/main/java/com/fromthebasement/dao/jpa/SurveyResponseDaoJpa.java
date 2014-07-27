package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.SurveyResponseDao;
import com.fromthebasement.model.SurveyResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
@Repository("surveyResponseDao")
public class SurveyResponseDaoJpa extends GenericDaoJpa<SurveyResponse,Long> implements SurveyResponseDao {
    public SurveyResponseDaoJpa() {
        super(SurveyResponse.class);
    }

    public List<SurveyResponse> getAllActive(Long userId) {
        Query query = getEntityManager().createQuery("select new SurveyResponse(s,lp) from LeaguePlayer lp inner join lp.player p inner join lp.league l inner join l.surveyFeed sf inner join sf.surveys s where p.user.id = :userId and s.active = true");
        query.setParameter("userId", userId);
        List<SurveyResponse> surveyResponses = query.getResultList();

        return surveyResponses;
    }
}
