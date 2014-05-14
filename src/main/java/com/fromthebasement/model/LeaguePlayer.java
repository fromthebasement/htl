package com.fromthebasement.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jeffginn on 5/7/14.
 */
@XmlRootElement
@Entity
public class LeaguePlayer {
    private Long        id;
    private League      league;
    private Player      player;
    private boolean     isCommissioner;

    public LeaguePlayer()
    {}

    public LeaguePlayer( League league, Player player )
    {
        this.league = league;
        this.player = player;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "league_id")
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "player_id")
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCommissioner() {
        return isCommissioner;
    }

    public void setCommissioner(boolean isCommissioner) {
        this.isCommissioner = isCommissioner;
    }
}
