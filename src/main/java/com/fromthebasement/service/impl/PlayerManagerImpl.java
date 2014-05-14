package com.fromthebasement.service.impl;

import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.Player;
import com.fromthebasement.service.PlayerManager;
import org.springframework.stereotype.Service;


/**
 * Created by jeffginn on 5/12/14.
 */
@Service("playerManager")
public class PlayerManagerImpl extends GenericManagerImpl<Player, Long> implements PlayerManager {
    PlayerDao playerDao;

    @Override
    public Player create(Player player) {
        return playerDao.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerDao.save(player);
    }
}
