package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.Question;
import com.fromthebasement.model.Survey;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.model.SurveyResponseForm;
import com.fromthebasement.service.SurveyResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
@Controller
@RequestMapping("/api/v1/surveyResponses")
public class SurveyResponseController extends HTLController
{
    @Autowired
    private SurveyResponseManager surveyResponseManager;

    @Transactional( readOnly = true )
    @RequestMapping(value = "/leaguePlayers/{leaguePlayerId}/surveys/{surveyId}", method = RequestMethod.GET )
    public SurveyResponseForm get(@PathVariable("leaguePlayerId") long leaguePlayerId,
                              @PathVariable("surveyId") long surveyId)
    {
        return surveyResponseManager.get( leaguePlayerId, surveyId );
    }

    @Transactional( readOnly = true )
    @RequestMapping(value = "{id}", method = RequestMethod.GET )
    public SurveyResponse get(@PathVariable("id") long id)
    {
        return surveyResponseManager.get( id );
    }

    @Transactional( readOnly = true )
    @RequestMapping( value="/active", method = RequestMethod.GET )
    public List<SurveyResponse> getActive()
    {
        return surveyResponseManager.getAllActive();
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.POST )
    public SurveyResponseForm save(@RequestBody SurveyResponseForm surveyResponseForm)
    {
        return surveyResponseManager.save( surveyResponseForm );
    }
}
