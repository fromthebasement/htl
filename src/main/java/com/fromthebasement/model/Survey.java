package com.fromthebasement.model;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.skyscreamer.yoga.annotations.Core;

import java.util.List;

/**
 * Created by jeffginn on 4/13/14.
 */
@Entity
public class Survey extends BaseObject {
    private Long            id;
    private String          name;
    private Tattoo          tattoo;
    private DateTime        endTime;
    private SurveyFeed      surveyFeed;
    private List<Question>  questions;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Core
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length=50)
    @Core
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    public Tattoo getTattoo() {
        return tattoo;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name="surveyFeed_id")
    public SurveyFeed getSurveyFeed() {
        return surveyFeed;
    }

    public void setSurveyFeed(SurveyFeed surveyFeed) {
        this.surveyFeed = surveyFeed;
    }

    @OneToMany(mappedBy="survey", targetEntity = Question.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (name != null ? !name.equals(survey.name) : survey.name != null) return false;
        if (tattoo != null ? !tattoo.equals(survey.tattoo) : survey.tattoo != null) return false;

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
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tattoo=" + tattoo +
                '}';
    }
}
