package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.model.League;
import com.fromthebasement.service.LeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffginn on 4/19/14.
 */
@Service("leagueManager")
public class LeagueManagerImpl extends GenericManagerImpl<League, Long> implements LeagueManager {
    LeagueDao leagueDao;

    @Autowired
    public LeagueManagerImpl(LeagueDao leagueDao) {
        super(leagueDao);
        this.leagueDao = leagueDao;
    }

    public List<League> getLeagues() {
        return leagueDao.getAll();
    }
}
