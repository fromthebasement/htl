package com.fromthebasement.security;

import com.fromthebasement.model.LoginOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeffginn on 4/8/14.
 */
public interface HTLApp {
    SecurityContext login(LoginOptions credentials, HttpServletRequest request);

    boolean logout( String token );

}
