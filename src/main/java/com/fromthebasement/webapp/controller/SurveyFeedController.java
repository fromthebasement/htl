package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.service.SurveyFeedManager;
import com.fromthebasement.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jeffginn on 6/1/14.
 */
@Controller
@RequestMapping("api/v1/surveyFeeds")
public class SurveyFeedController
{
    @Autowired
    private SurveyFeedManager surveyFeedManager;

    @Transactional( readOnly = true )
    @RequestMapping(value = "{id}", method = RequestMethod.GET )
    public SurveyFeed get(@PathVariable("id") long id)
    {
        return surveyFeedManager.get( id );
    }
}
