package com.fromthebasement.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jeffginn on 8/30/14.
 */
public class Standings {
    private ArrayList<StandingsEntry> entries = new ArrayList<StandingsEntry>();

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

    public Standings sort()
    {
        Collections.sort(getEntries(), new StandingsEntryComparator() );
        return this;
    }
}
