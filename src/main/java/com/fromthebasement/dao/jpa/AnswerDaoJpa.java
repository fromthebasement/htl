package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.AnswerDao;
import com.fromthebasement.model.Answer;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 7/13/14.
 */
@Repository("answerDao")
public class AnswerDaoJpa extends GenericDaoJpa<Answer,Long> implements AnswerDao {
    public AnswerDaoJpa() {
        super(Answer.class);
    }
}
