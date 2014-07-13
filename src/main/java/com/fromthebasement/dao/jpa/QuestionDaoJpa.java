package com.fromthebasement.dao.jpa;

import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.dao.QuestionDao;
import com.fromthebasement.model.Question;
import org.springframework.stereotype.Repository;

/**
 * Created by jeffginn on 7/13/14.
 */
@Repository("questionDao")
public class QuestionDaoJpa extends GenericDaoJpa<Question,Long> implements QuestionDao {
    public QuestionDaoJpa() { super( Question.class ); }
}
