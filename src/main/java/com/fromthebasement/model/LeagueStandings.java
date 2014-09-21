package com.fromthebasement.model;

import com.fromthebasement.service.impl.LeaguePlayerManagerImpl;

import java.util.*;

/**
 * Created by jeffginn on 9/7/14.
 */
public class LeagueStandings {
    private ArrayList<SurveyStandings> surveyStandings = new ArrayList<SurveyStandings>();

    public ArrayList<SurveyStandings> getSurveyStandings() {
        return surveyStandings;
    }

    public void setSurveyStandings(ArrayList<SurveyStandings> surveyStandings) {
        this.surveyStandings = surveyStandings;
    }

    public void addSurveyStandings( SurveyStandings surveyStandings )
    {
        this.surveyStandings.add( surveyStandings );
    }

    public LeagueStandings sort()
    {
        Collections.sort(getSurveyStandings(), new SurveyStandingsComparator());
        return this;
    }

    public ArrayList<StandingsEntry> getEntries() {
        ArrayList<StandingsEntry> entries = new ArrayList<>();

        Map<LeaguePlayer,Integer> scores = new HashMap<>();

        for( SurveyStandings surveyStandings : this.surveyStandings )
        {
            for( StandingsEntry standingsEntry : surveyStandings.getEntries() ) {
                int score = 0;
                LeaguePlayer leaguePlayer = standingsEntry.getLeaguePlayer();

                if( scores.containsKey( leaguePlayer ) )
                    score = scores.get( leaguePlayer );

                score += standingsEntry.getScore();

                scores.put( leaguePlayer, score );
            }
        }

        for( Iterator<Map.Entry<LeaguePlayer,Integer>> it = scores.entrySet().iterator(); it.hasNext();  )
        {
            Map.Entry<LeaguePlayer,Integer> playerEntry = it.next();
            StandingsEntry standingsEntry = new StandingsEntry();
            standingsEntry.setLeaguePlayer( playerEntry.getKey() );
            standingsEntry.setScore( playerEntry.getValue() );

            entries.add(standingsEntry);
        }

        Collections.sort(entries, new StandingsEntryComparator());

        return entries;
    }
}
