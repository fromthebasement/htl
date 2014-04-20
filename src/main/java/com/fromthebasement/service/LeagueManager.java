package com.fromthebasement.service;

import com.fromthebasement.model.League;
import com.fromthebasement.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by jeffginn on 4/19/14.
 */
@Path("/leagues")
public interface LeagueManager {
    @GET
    List<League> getLeagues();
}
