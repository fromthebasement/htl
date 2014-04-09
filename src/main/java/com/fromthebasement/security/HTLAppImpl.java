package com.fromthebasement.security;

import com.fromthebasement.model.LoginOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by jeffginn on 4/8/14.
 */
@Component
public class HTLAppImpl implements HTLApp {
    @Resource(name = "authenticationManager")
    AuthenticationManager authenticationManager;

    @Override
    public SecurityContext login(LoginOptions credentials, HttpServletRequest request) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        SecurityContext securityContext = null;

        // Authenticate the user
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        }
        catch(BadCredentialsException e){
            throw new WebApplicationException( e, Response.Status.BAD_REQUEST );
        }

        if( request == null )
            return securityContext;

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return securityContext;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }
}
