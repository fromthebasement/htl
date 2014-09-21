package com.fromthebasement.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jeffginn on 8/30/14.
 */
public class SurveyStandings {
    private Survey survey;
    private ArrayList<StandingsEntry> entries = new ArrayList<StandingsEntry>();

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public ArrayList<StandingsEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<StandingsEntry> entries) {
        this.entries = entries;
    }

    public void addEntry( StandingsEntry entry )
    {
        this.entries.add( entry );
    }

    public SurveyStandings sort()
    {
        Collections.sort(getEntries(), new StandingsEntryComparator() );
        return this;
    }
}
