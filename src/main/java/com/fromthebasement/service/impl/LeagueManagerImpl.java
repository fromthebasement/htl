package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.League;
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

    @Autowired
    public LeagueManagerImpl(LeagueDao leagueDao, PlayerDao playerDao) {
        super(leagueDao);
        this.leagueDao = leagueDao;
        this.playerDao = playerDao;
    }

    @Override
    public List<League> getLeagues() {
        return leagueDao.getAll();
    }

    @Override
    public League createLeague( League league ) {
        return leagueDao.save( league );
    }

    @Override
    public Set<Player> getPlayers(Long id) {
        League league = leagueDao.get(id);

        return league.getPlayers();
    }

    @Override
    public Set<Player> updatePlayers(Long id, @RequestBody Player[] players) {
        League league = leagueDao.get(id);

        Set<Player> currentPlayers = new HashSet<Player>();

        for( Player player : players ) {
            currentPlayers.add(playerDao.save(player));
        }

        league.setPlayers( currentPlayers );

        return league.getPlayers();
    }

    @Override
    public Player addPlayer(Long id, Player player) {
        League league = leagueDao.get(id);

        if( player.getId() == null )
            player = playerDao.save( player );

        league.addPlayer(player);

        return player;
    }
}
