package com.fromthebasement.service;

import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 5/14/14.
 */
@Path("/surveyFeeds")
public interface SurveyFeedManager extends GenericManager<SurveyFeed,Long> {
    @GET
    List<SurveyFeed> getAll();

    List<SurveyFeed> getAll(User user);

    @GET
    @Path("{id}")
    SurveyFeed get(@PathParam("id") Long id);
}
