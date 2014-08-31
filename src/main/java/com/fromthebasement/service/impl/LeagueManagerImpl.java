package com.fromthebasement.service.impl;

import com.fromthebasement.dao.LeagueDao;
import com.fromthebasement.dao.LeaguePlayerDao;
import com.fromthebasement.dao.PlayerDao;
import com.fromthebasement.model.*;
import com.fromthebasement.service.LeagueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Standings getStandings( Long id )
    {
        List<SurveyResponse> surveyResponses = leagueDao.getAllCompleteSurveyResponses( id );

        Map<LeaguePlayer,Integer> scores = new HashMap<>();

        for( SurveyResponse surveyResponse : surveyResponses )
        {
            LeaguePlayer leaguePlayer = surveyResponse.getLeaguePlayer();
            int score = 0;
            if( scores.containsKey( leaguePlayer ) ) {
                score = scores.get( leaguePlayer );
            }

            score += getScore( surveyResponse );

            scores.put( leaguePlayer, score );
        }

        Standings standings = new Standings();

        for( Iterator<Map.Entry<LeaguePlayer,Integer>> entries = scores.entrySet().iterator(); entries.hasNext();  )
        {
            Map.Entry<LeaguePlayer,Integer> entry = entries.next();
            StandingsEntry standingsEntry = new StandingsEntry();
            standingsEntry.setLeaguePlayer( entry.getKey() );
            standingsEntry.setScore( entry.getValue() );

            standings.addEntry( standingsEntry );
        }

        return standings.sort();
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
