package com.fromthebasement.service;

import com.fromthebasement.model.Player;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * Created by jeffginn on 5/12/14.
 */
@Path("/players")
public interface PlayerManager extends GenericManager<Player,Long> {
    @POST
    Player create( Player player );

    @PUT
    Player update( Player player );
}
