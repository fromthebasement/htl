package com.fromthebasement.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 4/15/14.
 */
@Entity
public class SurveyFeed extends BaseObject {
    private Long            id;
    private String          name;
    private Tattoo          tattoo;
    private List<Survey>    surveys;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length=50)
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

    @OneToMany(mappedBy="surveyFeed", targetEntity = Survey.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyFeed that = (SurveyFeed) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tattoo != null ? !tattoo.equals(that.tattoo) : that.tattoo != null) return false;

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
        return "SurveyFeed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tattoo=" + tattoo +
                ", surveys=" + surveys +
                '}';
    }
}
