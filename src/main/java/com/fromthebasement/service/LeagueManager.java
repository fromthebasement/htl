package com.fromthebasement.service;

import com.fromthebasement.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 4/19/14.
 */
public interface LeagueManager extends GenericManager<League, Long> {
    List<League> getAll();

    Set<LeaguePlayer> getLeaguePlayers(Long id);

    League create( League league );

    LeagueStandings getStandings( Long id );
}
