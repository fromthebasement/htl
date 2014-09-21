package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.model.League;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.model.SurveyResponseFabrication;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jeffginn on 4/19/14.
 */
@Repository("leagueDao")
public class LeagueDaoJpa extends GenericDaoJpa<League, Long> implements LeagueDao {
    public LeagueDaoJpa() {
        super(League.class);
    }

    @Override
    /**
     * Returns all completed survey responses for any player in the league
     */
    public List<SurveyResponse> getAllCompleteSurveyResponses(Long leagueId)
    {
        TypedQuery<SurveyResponse> query = getEntityManager().createQuery("select sr from SurveyResponse sr inner join sr.leaguePlayer lp WHERE lp.league.id = :leagueId and sr.survey.active = true", SurveyResponse.class );
        query.setParameter( "leagueId", leagueId );

        return query.getResultList();
    }
}
