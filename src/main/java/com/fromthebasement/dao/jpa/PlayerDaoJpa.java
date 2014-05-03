package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.Player;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 4/20/14.
 */
@Repository("playerDao")
public class PlayerDaoJpa extends GenericDaoJpa<Player, Long> implements PlayerDao {

    public PlayerDaoJpa() {
        super(Player.class);
    }
}

