package com.fromthebasement.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by jeffginn on 8/2/14.
 */
public class SurveyResponseFormEntry {
    private Question    question;
    private long        answerId = -1;
    private long        score = -1;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    @JsonIgnore
    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
