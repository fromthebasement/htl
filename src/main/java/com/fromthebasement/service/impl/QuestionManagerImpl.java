package com.fromthebasement.service.impl;

import com.fromthebasement.dao.QuestionDao;
import com.fromthebasement.model.Question;
import com.fromthebasement.service.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jeffginn on 7/13/14.
 */
@Service("questionManager")
public class QuestionManagerImpl extends GenericManagerImpl<Question, Long> implements QuestionManager {

    @Autowired
    public QuestionManagerImpl( QuestionDao questionDao ) {
        super(questionDao);
    }
}
