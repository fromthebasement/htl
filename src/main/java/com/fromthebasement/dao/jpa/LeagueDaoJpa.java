package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.model.League;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 4/19/14.
 */
@Repository("leagueDao")
public class LeagueDaoJpa extends GenericDaoJpa<League, Long> implements LeagueDao {
    public LeagueDaoJpa() {
        super(League.class);
    }
}
