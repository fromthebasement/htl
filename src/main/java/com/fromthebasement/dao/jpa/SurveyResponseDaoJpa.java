package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.SurveyResponseDao;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.model.SurveyResponseFabrication;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
@Repository("surveyResponseDao")
public class SurveyResponseDaoJpa extends GenericDaoJpa<SurveyResponse,Long> implements SurveyResponseDao {
    public SurveyResponseDaoJpa() {
        super(SurveyResponse.class);
    }

    public List<SurveyResponse> getAllActive(long userId, long leagueId) {
        Query query = getEntityManager().createQuery("select new SurveyResponse(s,lp) from LeaguePlayer lp inner join lp.player p inner join lp.league l inner join l.surveyFeed sf inner join sf.surveys s where p.user.id = :userId and s.active = true and l.id = :leagueId");
        query.setParameter("userId", userId);
        query.setParameter("leagueId", leagueId);
        List<SurveyResponse> surveyResponses = query.getResultList();

        return surveyResponses;
    }

    @Override
    public SurveyResponse get(long leaguePlayerId, long surveyId) {
        TypedQuery<SurveyResponseFabrication> query = getEntityManager().createQuery("select new com.fromthebasement.model.SurveyResponseFabrication((select sr from SurveyResponse sr WHERE sr.leaguePlayer.id = :leaguePlayerId and sr.survey.id = :surveyId),s,lp) from LeaguePlayer lp inner join lp.player p inner join lp.league l inner join l.surveyFeed sf inner join sf.surveys s where lp.id = :leaguePlayerId and s.id = :surveyId", SurveyResponseFabrication.class);

        query.setParameter("leaguePlayerId", leaguePlayerId);
        query.setParameter("surveyId", surveyId);

        SurveyResponseFabrication pair = query.getSingleResult();

        if( pair.getResponse() != null )
            return pair.getResponse();

        return pair.getFabricatedResponse();
    }
}
