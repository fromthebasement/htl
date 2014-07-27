package com.fromthebasement.service.impl;

import com.fromthebasement.dao.SurveyResponseDao;
import com.fromthebasement.model.SurveyResponse;
import com.fromthebasement.service.SurveyResponseManager;
import com.fromthebasement.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffginn on 7/26/14.
 */
@Service("surveyResponseManager")
public class SurveyResponseManagerImpl extends GenericManagerImpl<SurveyResponse,Long> implements SurveyResponseManager {
    UserManager         userManager;
    SurveyResponseDao   surveyResponseDao;

    @Autowired
    public SurveyResponseManagerImpl(SurveyResponseDao surveyResponseDao, UserManager userManager) {
        super(surveyResponseDao);
        this.surveyResponseDao = surveyResponseDao;
        this.userManager = userManager;
    }

    @Override
    public List<SurveyResponse> getAllActive() {
        return surveyResponseDao.getAllActive(UserManagerImpl.getCurrentUser().getId());
    }
}
