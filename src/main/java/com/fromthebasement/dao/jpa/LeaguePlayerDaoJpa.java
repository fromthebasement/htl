package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.LeaguePlayerDao;
import com.fromthebasement.model.League;
import com.fromthebasement.model.LeaguePlayer;
import com.fromthebasement.model.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by jeffginn on 5/8/14.
 */
@Repository("leaguePlayerDao")
public class LeaguePlayerDaoJpa extends GenericDaoJpa<LeaguePlayer, Long> implements LeaguePlayerDao {
    public LeaguePlayerDaoJpa() {
        super(LeaguePlayer.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LeaguePlayer get(League league, Player player) {
        Query q = getEntityManager().createQuery("select lp from LeaguePlayer lp where lp.league=? AND lp.player=?");
        q.setParameter(1, league);
        q.setParameter(2, player);

        Object result = q.getSingleResult();
        return (LeaguePlayer)result;
    }
}
