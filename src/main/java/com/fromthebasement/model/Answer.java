package com.fromthebasement.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.skyscreamer.yoga.annotations.Core;

import javax.persistence.*;

/**
 * Created by jeffginn on 5/14/14.
 */
@Entity
public class Answer extends BaseObject {
    private Long        id;
    private String      name;
    private Tattoo      tattoo;
    private Question    question;

    @Id
    @Core
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length=256)
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
    @JoinColumn(name="question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (name != null ? !name.equals(answer.name) : answer.name != null) return false;
        if (!question.equals(answer.question)) return false;
        if (tattoo != null ? !tattoo.equals(answer.tattoo) : answer.tattoo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tattoo != null ? tattoo.hashCode() : 0);
        result = 31 * result + question.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tattoo=" + tattoo +
                ", question=" + question +
                '}';
    }
}
