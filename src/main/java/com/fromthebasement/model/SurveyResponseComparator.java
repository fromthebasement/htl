package com.fromthebasement.model;

import java.util.Comparator;

/**
 * Created by jeffginn on 8/31/14.
 */
public class SurveyResponseComparator implements Comparator<SurveyResponse> {
    @Override
    public int compare(SurveyResponse a, SurveyResponse b )
    {
        if( a == null || b == null )
            return 0;

        return new SurveyComparator().compare(a.getSurvey(), b.getSurvey());
    }
}