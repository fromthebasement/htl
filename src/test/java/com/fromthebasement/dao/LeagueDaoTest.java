package com.fromthebasement.dao;

import com.fromthebasement.model.League;
import com.fromthebasement.model.Survey;
import com.fromthebasement.model.Tattoo;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeffginn on 4/13/14.
 */
public class LeagueDaoTest extends BaseDaoTestCase {
    @Autowired
    private LeagueDao leagueDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveLeague() throws Exception {
        League league = new League();
        league.setName( "The mighty cubs" );

        Tattoo tattoo = new Tattoo();
        tattoo.setImageUrl( "/images/tigers-logo.png" );
        tattoo.setSlogan( "Best cub-based league ever" );

        league.setTattoo( tattoo );

        leagueDao.save(league);

        league = leagueDao.get(league.getId());

        assertEquals("The mighty cubs", league.getName());
        assertEquals("Best cub-based league ever", league.getTattoo().getSlogan());

        assertNotNull(league.getId());

        log.debug("removing league...");

        leagueDao.remove(league.getId());

        // should throw DataAccessException
        leagueDao.get(league.getId());
    }
}
