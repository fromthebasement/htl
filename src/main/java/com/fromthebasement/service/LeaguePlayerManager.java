package com.fromthebasement.service;

import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.Person;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 5/9/14.
 */
@Path("/leaguePlayers")
public interface LeaguePlayerManager extends GenericManager<LeaguePlayer, Long> {
    public Set<LeaguePlayer> updateLeaguePlayers(@RequestBody LeaguePlayer[] leaguePlayers);
}
