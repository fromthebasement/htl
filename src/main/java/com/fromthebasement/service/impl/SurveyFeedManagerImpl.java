package com.fromthebasement.service.impl;

import com.fromthebasement.dao.SurveyFeedDao;
import com.fromthebasement.model.SurveyFeed;
import com.fromthebasement.model.User;
import com.fromthebasement.service.SurveyFeedManager;
import com.fromthebasement.service.UserManager;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 5/14/14.
 */
@Service("surveyFeedManager")
public class SurveyFeedManagerImpl extends GenericManagerImpl<SurveyFeed,Long> implements SurveyFeedManager {
    SurveyFeedDao   surveyFeedDao;
    UserManager     userManager;

    @Autowired
    public SurveyFeedManagerImpl(SurveyFeedDao surveyFeedDao, UserManager userManager) {
        super(surveyFeedDao);
        this.surveyFeedDao = surveyFeedDao;
        this.userManager = userManager;
    }

    @Override
    public List<SurveyFeed> getAll() {
        return getAll(userManager.get(UserManagerImpl.getCurrentUser().getId()));
    }

    @Override
    public List<SurveyFeed> getAll(User user) {

        return new ArrayList( user.getSurveyFeeds() );
    }
}
