package com.fromthebasement.model;

import java.util.Comparator;

/**
 * Created by jeffginn on 8/23/14.
 */
public class SurveyResponseFormEntryComparator implements Comparator<SurveyResponseFormEntry> {
    @Override
    public int compare(SurveyResponseFormEntry a, SurveyResponseFormEntry b )
    {
        if( a == null || b == null )
            return 0;

        return -1 * Long.compare( a.getScore(), b.getScore() );
    }
}