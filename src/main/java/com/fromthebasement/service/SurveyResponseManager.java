package com.fromthebasement.service;

import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.model.SurveyResponseForm;
import com.fromthebasement.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
public interface SurveyResponseManager extends GenericManager<SurveyResponse,Long> {
    List<SurveyResponse> getAllActive();

    SurveyResponseForm get(long leaguePlayerId, long surveyId);

    SurveyResponseForm save(SurveyResponseForm surveyResponseForm);
}
