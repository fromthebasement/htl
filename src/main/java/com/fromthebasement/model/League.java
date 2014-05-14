package com.fromthebasement.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by jeffginn on 4/19/14.
 */
@XmlRootElement
@Entity
public class League extends BaseObject {
    private Long                id;
    private String              name;
    private Tattoo              tattoo;
    private Set<LeaguePlayer>   leaguePlayers = new HashSet<LeaguePlayer>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tattoo getTattoo() {
        return tattoo;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "league", cascade=CascadeType.ALL)
    public Set<LeaguePlayer> getLeaguePlayers() {
        return leaguePlayers;
    }

    /**
     * Adds a league player to the league
     *
     * @param leaguePlayer the fully instantiated league player
     */
    public void addLeaguePlayer(LeaguePlayer leaguePlayer) {
        getLeaguePlayers().add( leaguePlayer );
    }

    public void setLeaguePlayers(Set<LeaguePlayer> leaguePlayers) {
        this.leaguePlayers = leaguePlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        League league = (League) o;

        if (name != null ? !name.equals(league.name) : league.name != null) return false;
        if (tattoo != null ? !tattoo.equals(league.tattoo) : league.tattoo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tattoo != null ? tattoo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tattoo=" + tattoo +
                '}';
    }
}
