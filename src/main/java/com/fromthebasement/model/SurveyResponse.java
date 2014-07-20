package com.fromthebasement.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.skyscreamer.yoga.annotations.Core;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 7/20/14.
 */
@Entity
public class SurveyResponse extends BaseObject {
    private Long                    id;
    private Survey                  survey;
    private LeaguePlayer            leaguePlayer;
    private List<ConfidencePoint>   confidencePoints;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Core
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name="survey_id")
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name="league_player_id")
    public LeaguePlayer getLeaguePlayer() {
        return leaguePlayer;
    }

    public void setLeaguePlayer(LeaguePlayer leaguePlayer) {
        this.leaguePlayer = leaguePlayer;
    }

    @OneToMany(mappedBy="surveyResponse", targetEntity = ConfidencePoint.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    public List<ConfidencePoint> getConfidencePoints() {
        return confidencePoints;
    }

    public void setConfidencePoints(List<ConfidencePoint> confidencePoints) {
        this.confidencePoints = confidencePoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyResponse that = (SurveyResponse) o;

        if (confidencePoints != null ? !confidencePoints.equals(that.confidencePoints) : that.confidencePoints != null)
            return false;
        if (!leaguePlayer.equals(that.leaguePlayer)) return false;
        if (!survey.equals(that.survey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = survey.hashCode();
        result = 31 * result + leaguePlayer.hashCode();
        result = 31 * result + (confidencePoints != null ? confidencePoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SurveyResponse{" +
                "survey=" + survey +
                ", leaguePlayer=" + leaguePlayer +
                ", confidencePoints=" + confidencePoints +
                '}';
    }
}
