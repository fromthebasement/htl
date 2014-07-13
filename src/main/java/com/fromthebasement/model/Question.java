package com.fromthebasement.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.skyscreamer.yoga.annotations.Core;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jeffginn on 4/14/14.
 */
@Entity
public class Question extends BaseObject {
    private Long            id;
    private String          name;
    private Tattoo          tattoo;
    private Survey          survey;
    private List<Answer>    answers;

    @Id
    @Core
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Tattoo getTattoo() {
        return tattoo;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    @ManyToOne
    @JoinColumn(name="survey_id")
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @OneToMany(mappedBy="question", targetEntity = Answer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (name != null ? !name.equals(question.name) : question.name != null) return false;
        if (tattoo != null ? !tattoo.equals(question.tattoo) : question.tattoo != null) return false;

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
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tattoo=" + tattoo +
                '}';
    }
}
