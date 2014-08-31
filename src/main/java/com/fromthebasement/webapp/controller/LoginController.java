package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.LoginOptions;
import com.fromthebasement.model.User;
import com.fromthebasement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeffginn on 6/1/14.
 */
@Controller
@RequestMapping("/api/v1/login")
public class LoginController extends HTLController {

    @Autowired
    private LoginService loginService;

    @Transactional( readOnly = false )
    @RequestMapping( method = RequestMethod.POST )
    public User login(@RequestBody LoginOptions loginOptions, HttpServletRequest request, Model model)
    {
        // Clear the binding result from the model, necessary for yoga to serialize properly
        model.asMap().clear();

        return loginService.login(loginOptions,request);
    }
}
