package com.fromthebasement.service;

import com.fromthebasement.model.LoginOptions;
import com.fromthebasement.model.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by jeffginn on 4/7/14.
 */
@Path("/logout")
public interface LogoutService {
    @POST
    boolean logout();
}
