package com.fromthebasement.model;

import java.util.Comparator;

/**
 * Created by jeffginn on 8/31/14.
 */
public class StandingsEntryComparator implements Comparator<StandingsEntry> {
    @Override
    public int compare(StandingsEntry a, StandingsEntry b )
    {
        if( a == null || b == null )
            return 0;

        int result =  -1 * Long.compare( a.getScore(), b.getScore() );

        if( result == 0 )
        {
            result = a.getLeaguePlayer().getPlayer().getName().compareTo( b.getLeaguePlayer().getPlayer().getName() );
        }

        return result;
    }
}