package com.fromthebasement.dao;

import com.fromthebasement.model.*;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeffginn on 4/13/14.
 */
public class LeagueDaoTest extends BaseDaoTestCase {
    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private LeaguePlayerDao leaguePlayerDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetPlayers() {
        League league = new League();
        league.setName( "The mighty cubs" );

        Tattoo tattoo = new Tattoo();
        tattoo.setImageUrl( "/images/tigers-logo.png" );
        tattoo.setSlogan( "Best cub-based league ever" );

        league.setTattoo( tattoo );

        Player player1 = new Player();
        player1.setName( "Player 1" );

        LeaguePlayer leaguePlayer1 = new LeaguePlayer();
        leaguePlayer1.setPlayer( player1 );
        leaguePlayer1.setLeague( league );

        leaguePlayer1 = leaguePlayerDao.save( leaguePlayer1 );

        Player player2 = new Player();
        player2.setName( "Player 2" );

        LeaguePlayer leaguePlayer2 = new LeaguePlayer();
        leaguePlayer2.setPlayer( player2 );
        leaguePlayer2.setLeague( league );

        leaguePlayer2 = leaguePlayerDao.save( leaguePlayer2 );

        assertNotNull( league.getId() );
    }

    @Test
    public void testAddAndRemoveLeague() throws Exception {
        League league = new League();
        league.setName( "The mighty cubs" );

        Tattoo tattoo = new Tattoo();
        tattoo.setImageUrl( "/images/tigers-logo.png" );
        tattoo.setSlogan( "Best cub-based league ever" );

        league.setTattoo( tattoo );

        league = leagueDao.save(league);

        assertNotNull(league.getId());

        league = leagueDao.get(league.getId());

        assertEquals("The mighty cubs", league.getName());
        assertEquals("Best cub-based league ever", league.getTattoo().getSlogan());

        log.debug("removing league...");

        leagueDao.remove(league.getId());

        // should throw DataAccessException
        exception.expect(DataAccessException.class);
        leagueDao.get(league.getId());
    }
}
