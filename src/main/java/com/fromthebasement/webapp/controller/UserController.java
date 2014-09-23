package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.LoginOptions;
import com.fromthebasement.model.User;
import com.fromthebasement.service.LoginService;
import com.fromthebasement.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeffginn on 6/1/14.
 */
@Controller
@RequestMapping("/api/v1/user")
public class UserController extends HTLController {
    @Autowired
    UserManager userManager;

    @RequestMapping( method = RequestMethod.GET )
    public User getInfo()
    {
        return userManager.getCurrentUserWithDefaultLeaguePlayer();
    }
}
