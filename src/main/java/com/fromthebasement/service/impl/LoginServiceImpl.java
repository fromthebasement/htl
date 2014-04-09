package com.fromthebasement.service.impl;

import com.fromthebasement.model.LoginOptions;
import com.fromthebasement.model.User;
import com.fromthebasement.security.HTLApp;
import com.fromthebasement.service.LoginService;
import com.fromthebasement.service.UserManager;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by jeffginn on 4/8/14.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    HTLApp htlApp;

    @Autowired
    UserManager userManager;

    @Context
    private MessageContext context;

    @Override
    public User login(LoginOptions loginOptions) {
        HttpServletRequest request = context.getHttpServletRequest();
        htlApp.login( loginOptions, request );
        User user = userManager.getUserByUsername(request.getRemoteUser());
        return user;
    }
}
