package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.LeaguePlayerDao;
import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.service.LeaguePlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeffginn on 5/9/14.
 */
@Service("leaguePlayerManager")
public class LeaguePlayerManagerImpl extends GenericManagerImpl<LeaguePlayer, Long> implements LeaguePlayerManager {
    LeaguePlayerDao leaguePlayerDao;

    @Autowired
    public LeaguePlayerManagerImpl(LeaguePlayerDao leaguePlayerDao) {
        super(leaguePlayerDao);
        this.leaguePlayerDao = leaguePlayerDao;
    }

    @Override
    public Set<LeaguePlayer> updateLeaguePlayers(@RequestBody LeaguePlayer[] leaguePlayers) {
        Set<LeaguePlayer> currentLeaguePlayers = new HashSet<LeaguePlayer>();

        for( LeaguePlayer leaguePlayer : leaguePlayers )
            currentLeaguePlayers.add(leaguePlayerDao.save(leaguePlayer));

        return currentLeaguePlayers;
    }
}
