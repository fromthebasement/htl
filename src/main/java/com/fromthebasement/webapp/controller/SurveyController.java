package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.Answer;
import com.fromthebasement.model.Question;
import com.fromthebasement.model.Survey;
import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.service.QuestionManager;
import com.fromthebasement.service.SurveyFeedManager;
import com.fromthebasement.service.SurveyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffginn on 6/1/14.
 */
@Controller
@RequestMapping("/api/v1/surveys")
public class SurveyController
{
    @Autowired
    private SurveyManager surveyManager;

    @Transactional( readOnly = true )
    @RequestMapping(value = "{id}", method = RequestMethod.GET )
    public Survey get(@PathVariable("id") long id)
    {
        return surveyManager.get( id );
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.POST )
    public Survey create(@RequestBody Survey survey)
    {
        survey =  surveyManager.save(survey);
        return survey;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.PUT )
    public Survey update(@RequestBody Survey survey)
    {
        Survey _existingSurvey = surveyManager.get(survey.getId());

        _existingSurvey.setName( survey.getName() );
        _existingSurvey.setActive( survey.isActive() );
        _existingSurvey.setEndTime( survey.getEndTime() );

        survey =  surveyManager.save(_existingSurvey);

        return survey;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.DELETE )
    @ResponseBody
    public boolean delete(@RequestBody Survey survey)
    {
        surveyManager.remove(survey);
        return true;
    }
}
