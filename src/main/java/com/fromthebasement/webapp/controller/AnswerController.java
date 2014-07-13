package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.Answer;
import com.fromthebasement.model.Question;
import com.fromthebasement.service.AnswerManager;
import com.fromthebasement.service.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jeffginn on 7/13/14.
 */
@Controller
@RequestMapping("/api/v1/answers")
public class AnswerController {
    @Autowired
    AnswerManager answerManager;

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.POST )
    public Answer create(@RequestBody Answer answer)
    {
        answer = answerManager.save(answer);
        return answer;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.DELETE )
    @ResponseBody
    public boolean delete(@RequestBody Answer answer)
    {
        answerManager.remove(answer);
        return true;
    }
}
