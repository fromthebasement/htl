package com.fromthebasement.model;

import org.skyscreamer.yoga.annotations.Core;

import javax.persistence.*;

/**
 * Created by jeffginn on 7/20/14.
 */
@Entity
public class ConfidencePoint {
    private Long            id;
    private SurveyResponse  surveyResponse;
    private Answer          answer;
    private int             score;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Core
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name="survey_response_id")
    public SurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name="answer_id")
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
