package com.fromthebasement.service.impl;

import com.fromthebasement.dao.SurveyDao;
import com.fromthebasement.model.Survey;
import com.fromthebasement.service.SurveyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jeffginn on 4/14/14.
 */
@Service("surveyManager")
public class SurveyManagerImpl extends GenericManagerImpl<Survey,Long> implements SurveyManager {
    SurveyDao surveyDao;

    @Autowired
    public SurveyManagerImpl(SurveyDao surveyDao) {
        super(surveyDao);
        this.surveyDao = surveyDao;
    }
}
