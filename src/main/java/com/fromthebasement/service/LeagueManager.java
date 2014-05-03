package com.fromthebasement.service;

import com.fromthebasement.model.League;
import com.fromthebasement.model.Person;
import com.fromthebasement.model.Player;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 4/19/14.
 */
@Path("/leagues")
public interface LeagueManager extends GenericManager<League, Long> {
    @GET
    List<League> getLeagues();

    @GET
    @Path("{id}/players")
    Set<Player> getPlayers(@PathParam("id") Long id);

    @POST
    League createLeague( League league );

    @PUT
    @Path("{id}/players")
    Set<Player> updatePlayers(@PathParam("id") Long id, @RequestBody Player[] players );

    @POST
    @Path("{id}/players")
    Player addPlayer(@PathParam("id") Long id, @RequestBody Player player);
}
