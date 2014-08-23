package com.fromthebasement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Interchange class used to pass a survey response for a given player back and forth with the client.
 */
public class SurveyResponseForm {
    SurveyResponse surveyResponse;
    private List<SurveyResponseFormEntry> entries = new ArrayList<SurveyResponseFormEntry>();

    public SurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public List<SurveyResponseFormEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SurveyResponseFormEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(SurveyResponseFormEntry surveyResponseFormEntry) {
        entries.add(surveyResponseFormEntry);
    }
}