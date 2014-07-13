package com.fromthebasement.service;

import com.fromthebasement.model.LoginOptions;
import com.fromthebasement.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by jeffginn on 4/7/14.
 */
@Path("/login")
public interface LoginService {
    @POST
    User login(LoginOptions loginOptions, HttpServletRequest request);
}
