package com.fromthebasement.model;

/**
 * Created by jeffginn on 7/30/14.
 */
public class SurveyResponseFabrication {
    private SurveyResponse response;
    private SurveyResponse fabricatedResponse;

    public SurveyResponseFabrication(SurveyResponse response, Survey survey, LeaguePlayer leaguePlayer){
        this.response = response;
        this.fabricatedResponse = new SurveyResponse(survey,leaguePlayer);
    }

    public SurveyResponse getResponse() {
        return response;
    }

    public void setResponse(SurveyResponse response) {
        this.response = response;
    }

    public SurveyResponse getFabricatedResponse() {
        return fabricatedResponse;
    }

    public void setFabricatedResponse(SurveyResponse fabricatedResponse) {
        this.fabricatedResponse = fabricatedResponse;
    }
}
