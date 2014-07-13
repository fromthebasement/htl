package com.fromthebasement.service.impl;

import com.fromthebasement.dao.AnswerDao;
import com.fromthebasement.dao.QuestionDao;
import com.fromthebasement.model.Answer;
import com.fromthebasement.model.Question;
import com.fromthebasement.service.AnswerManager;
import com.fromthebasement.service.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jeffginn on 7/13/14.
 */
@Service("answerManager")
public class AnswerManagerImpl extends GenericManagerImpl<Answer, Long> implements AnswerManager {
    @Autowired
    public AnswerManagerImpl(AnswerDao answerDao) {
        super(answerDao);
    }
}
