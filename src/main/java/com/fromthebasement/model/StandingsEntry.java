package com.fromthebasement.model;

/**
 * Created by jeffginn on 8/30/14.
 */
public class StandingsEntry {
    private LeaguePlayer leaguePlayer;
    private int score;

    public LeaguePlayer getLeaguePlayer() {
        return leaguePlayer;
    }

    public void setLeaguePlayer(LeaguePlayer leaguePlayer) {
        this.leaguePlayer = leaguePlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
