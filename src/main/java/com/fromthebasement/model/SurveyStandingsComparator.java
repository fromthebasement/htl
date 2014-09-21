package com.fromthebasement.model;

import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * Created by jeffginn on 8/31/14.
 */
public class SurveyStandingsComparator implements Comparator<SurveyStandings> {
    @Override
    public int compare(SurveyStandings a, SurveyStandings b )
    {
        if( a == null || b == null )
            return 0;

        return new SurveyComparator().compare(a.getSurvey(), b.getSurvey());
    }
}