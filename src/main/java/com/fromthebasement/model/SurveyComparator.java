package com.fromthebasement.model;

import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * Created by jeffginn on 9/7/14.
 */

/**
 * Compares surveys based on end time
 */
public class SurveyComparator implements Comparator<Survey> {
    @Override
    public int compare(Survey a, Survey b) {
        if( a == null && b == null )
            return 0;

        if( a == null )
            return -1;

        if( b == null )
            return 1;

        DateTime endTimeA = a.getEndTime();
        DateTime endTimeB = b.getEndTime();

        if( endTimeA == null && endTimeB == null )
            return 0;

        if( endTimeA == null )
            return -1;

        if( endTimeB == null )
            return 1;

        if( endTimeA.isBefore( endTimeB ) )
            return 1;

        if( endTimeA.isAfter( endTimeB ) )
            return -1;

        return 0;
    }
}
