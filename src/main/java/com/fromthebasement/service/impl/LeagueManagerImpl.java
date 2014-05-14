package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.LeaguePlayerDao;
import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.Player;
import com.fromthebasement.service.LeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 4/19/14.
 */
@Service("leagueManager")
public class LeagueManagerImpl extends GenericManagerImpl<League, Long> implements LeagueManager {
    LeagueDao leagueDao;

    PlayerDao playerDao;

    LeaguePlayerDao leaguePlayerDao;

    @Autowired
    public LeagueManagerImpl(LeagueDao leagueDao, PlayerDao playerDao) {
        super(leagueDao);
        this.leagueDao = leagueDao;
    }

    @Override
    public List<League> getAll() {
        return super.getAll();
    }

    @Override
    public League create( League league ) {
        return leagueDao.save( league );
    }

    @Override
    public Set<LeaguePlayer> getLeaguePlayers(Long id) {
        League league = leagueDao.get(id);

        return league.getLeaguePlayers();
    }

    public LeaguePlayer addPlayer(Long id, Player player) {
        League league = leagueDao.get(id);
        player = playerDao.save(player);

        LeaguePlayer leaguePlayer = leaguePlayerDao.get(league,player);

        if( leaguePlayer != null )
            return leaguePlayer;

        leaguePlayer = new LeaguePlayer(league,player);

        leaguePlayer = leaguePlayerDao.save(leaguePlayer);

        return leaguePlayer;
    }
}
