package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.LeaguePlayerDao;
import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.*;
import com.fromthebasement.service.LeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jeffginn on 4/19/14.
 */
@Service("leagueManager")
public class LeagueManagerImpl extends GenericManagerImpl<League, Long> implements LeagueManager {
    LeagueDao leagueDao;

    PlayerDao playerDao;

    LeaguePlayerDao leaguePlayerDao;

    @Autowired
    public LeagueManagerImpl(LeagueDao leagueDao, PlayerDao playerDao) {
        super(leagueDao);
        this.leagueDao = leagueDao;
    }

    @Override
    public List<League> getAll() {
        return super.getAll();
    }

    @Override
    public League create( League league ) {
        return leagueDao.save( league );
    }

    @Override
    public Set<LeaguePlayer> getLeaguePlayers(Long id) {
        League league = leagueDao.get(id);

        return league.getLeaguePlayers();
    }

    public LeaguePlayer addPlayer(Long id, Player player) {
        League league = leagueDao.get(id);
        player = playerDao.save(player);

        LeaguePlayer leaguePlayer = leaguePlayerDao.get(league,player);

        if( leaguePlayer != null )
            return leaguePlayer;

        leaguePlayer = new LeaguePlayer(league,player);

        leaguePlayer = leaguePlayerDao.save(leaguePlayer);

        return leaguePlayer;
    }

    public LeagueStandings getStandings( Long id )
    {
        List<SurveyResponse> surveyResponses = leagueDao.getAllCompleteSurveyResponses( id );

        // Compute a score for each response and group responses by survey
        Map<Survey, Map<LeaguePlayer,Integer>> scoresBySurvey = new HashMap<>();

        for( SurveyResponse surveyResponse : surveyResponses )
        {
            LeaguePlayer leaguePlayer = surveyResponse.getLeaguePlayer();
            Survey survey = surveyResponse.getSurvey();

            if( !scoresBySurvey.containsKey( survey ) ) {
                scoresBySurvey.put( survey, new HashMap<LeaguePlayer, Integer>() );
            }

            Map<LeaguePlayer,Integer> scores = scoresBySurvey.get( survey );
            scores.put( leaguePlayer, getScore( surveyResponse ) );
        }

        // Create a standings object for each survey and add it to total standings
        LeagueStandings leagueStandings = new LeagueStandings();

        for( Iterator<Map.Entry<Survey, Map<LeaguePlayer,Integer>>> it = scoresBySurvey.entrySet().iterator(); it.hasNext(); )
        {
            Map.Entry<Survey, Map<LeaguePlayer,Integer>> entry = it.next();
            Survey survey = entry.getKey();
            Map<LeaguePlayer,Integer> scores = entry.getValue();

            SurveyStandings surveyStandings = new SurveyStandings();
            surveyStandings.setSurvey( survey );

            for( Iterator<Map.Entry<LeaguePlayer,Integer>> entries = scores.entrySet().iterator(); entries.hasNext();  )
            {
                Map.Entry<LeaguePlayer,Integer> playerEntry = entries.next();
                StandingsEntry standingsEntry = new StandingsEntry();
                standingsEntry.setLeaguePlayer( playerEntry.getKey() );
                standingsEntry.setScore( playerEntry.getValue() );

                surveyStandings.addEntry( standingsEntry );
            }

            leagueStandings.addSurveyStandings( surveyStandings.sort() );
        }

        return leagueStandings.sort();
    }

    public int getScore( SurveyResponse surveyResponse )
    {
        int score = 0;
        for( ConfidencePoint confidencePoint : surveyResponse.getConfidencePoints() )
        {
            Answer answer = confidencePoint.getAnswer();
            Question question = confidencePoint.getQuestion();
            Answer correctAnswer = question.getCorrectAnswer();

            if( correctAnswer != null && answer == correctAnswer )
                score += confidencePoint.getScore();
        }

        return score;
    }
}
