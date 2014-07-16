package com.fromthebasement.webapp.controller;

import com.fromthebasement.model.Answer;
import com.fromthebasement.model.Question;
import com.fromthebasement.service.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jeffginn on 7/13/14.
 */
@Controller
@RequestMapping("/api/v1/questions")
public class QuestionController {
    @Autowired
    QuestionManager questionManager;

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.POST )
    public Question create(@RequestBody Question question)
    {
        question =  questionManager.save(question);
        return question;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.PUT )
    public Question update(@RequestBody Question question)
    {
        question =  questionManager.save(question);
        return question;
    }

    @Transactional( readOnly = false )
    @RequestMapping(method = RequestMethod.DELETE )
    @ResponseBody
    public boolean delete(@RequestBody Question question)
    {
        questionManager.remove(question);
        return true;
    }
}