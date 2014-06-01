package com.fromthebasement.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.skyscreamer.yoga.annotations.Core;
import org.skyscreamer.yoga.annotations.URITemplate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jeffginn on 4/15/14.
 */
@Entity
@URITemplate("/surveyFeeds/{id}")
public class SurveyFeed extends BaseObject {
    private Long            id;
    private String          name;
    private Tattoo          tattoo;
    private List<Survey>    surveys;
    private Set<User>       users = new HashSet<User>();

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

    @OneToMany(mappedBy="surveyFeed", targetEntity = Survey.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "user_survey_feed",
            joinColumns = { @JoinColumn(name = "survey_feed_id") },
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Adds a user to the survey feed
     *
     * @param user the fully instantiated user
     */
    public void addUser(User user) {
        getUsers().add(user);
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
