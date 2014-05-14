package com.fromthebasement.dao;

import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.Player;
import com.fromthebasement.model.Tattoo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeffginn on 5/11/14.
 */
public class LeaguePlayerDaoTest extends BaseDaoTestCase {
    @Autowired
    private LeaguePlayerDao leaguePlayerDao;

    @Autowired
    private LeagueDao leagueDao;

    @Test
    public void testGetPlayers() {
        League league = new League();
        league.setName( "The mighty cubs" );

        Tattoo tattoo = new Tattoo();
        tattoo.setImageUrl( "/images/tigers-logo.png" );
        tattoo.setSlogan( "Best cub-based league ever" );

        league.setTattoo( tattoo );

        Player player = new Player();
        player.setName( "Player" );

        LeaguePlayer leaguePlayer = new LeaguePlayer();
        leaguePlayer.setPlayer( player );
        leaguePlayer.setLeague( league );

        leaguePlayer = leaguePlayerDao.save( leaguePlayer );

        LeaguePlayer leaguePlayerQ = leaguePlayerDao.get( leaguePlayer.getLeague(), leaguePlayer.getPlayer() );

        assertEquals( leaguePlayer, leaguePlayerQ );
    }
}
