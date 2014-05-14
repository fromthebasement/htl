package com.fromthebasement.dao;

import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.Player;

/**
 * Created by jeffginn on 5/7/14.
 */
public interface LeaguePlayerDao extends GenericDao<LeaguePlayer, Long> {
    public LeaguePlayer get(League league, Player player);
}
