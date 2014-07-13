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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
