package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.LeagueStandings;
import com.fromthebasement.model.SurveyStandings;
import com.fromthebasement.service.LeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeffginn on 8/30/14.
 */
@Controller
@RequestMapping("/api/v1/leagues")
public class LeagueController extends HTLController {
    @Autowired
    private LeagueManager leagueManager;

    @Transactional( readOnly = true )
    @RequestMapping(value = "/{id}/standings", method = RequestMethod.GET )
    public LeagueStandings get(@PathVariable("id") long id)
    {
        return leagueManager.getStandings( id );
    }
}
