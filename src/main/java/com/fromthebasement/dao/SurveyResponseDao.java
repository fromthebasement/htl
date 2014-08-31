package com.fromthebasement.dao;

import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.SurveyResponse;

import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
public interface SurveyResponseDao extends GenericDao<SurveyResponse,Long> {
    public List<SurveyResponse> getAllActive(Long userId);

    public SurveyResponse get(long leaguePlayerId, long surveyId);
}
