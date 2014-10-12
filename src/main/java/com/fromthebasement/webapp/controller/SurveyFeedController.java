package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.Survey;
import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.model.User;
import com.fromthebasement.service.SurveyFeedManager;
import com.fromthebasement.service.UserManager;
import com.fromthebasement.service.impl.UserManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 6/1/14.
 */
@Controller
@RequestMapping("/api/v1/surveyFeeds")
public class SurveyFeedController extends HTLController
{
    @Autowired
    private SurveyFeedManager surveyFeedManager;

    @Transactional( readOnly = true )
    @RequestMapping(value = "{id}", method = RequestMethod.GET )
    public SurveyFeed get(@PathVariable("id") long id)
    {
        return surveyFeedManager.get( id );
    }

    @Transactional( readOnly = true )
    @RequestMapping( method = RequestMethod.GET )
    public List<SurveyFeed> getAll()
    {
        return surveyFeedManager.getAll();
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.POST )
    public SurveyFeed create(@RequestBody SurveyFeed surveyFeed)
    {
        Set<User> users = new HashSet<User>();
        users.add(UserManagerImpl.getCurrentUser());
        surveyFeed.setUsers( users );
        surveyFeed =  surveyFeedManager.save(surveyFeed);
        return surveyFeed;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.PUT )
    public SurveyFeed update(@RequestBody SurveyFeed surveyFeed)
    {
        SurveyFeed _existing = surveyFeedManager.get(surveyFeed.getId());

        _existing.setName( surveyFeed.getName() );

        surveyFeed =  surveyFeedManager.save(_existing);

        return surveyFeed;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.PUT, value = "/archive" )
    public SurveyFeed archive(@RequestBody SurveyFeed surveyFeed)
    {
        SurveyFeed _existing = surveyFeedManager.get(surveyFeed.getId());

        _existing.setArchived( surveyFeed.isArchived() );

        surveyFeed =  surveyFeedManager.save(_existing);

        return surveyFeed;
    }
}
