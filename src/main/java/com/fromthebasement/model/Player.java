package com.fromthebasement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jeffginn on 4/19/14.
 */
@XmlRootElement
@Entity
public class Player extends BaseObject {
    private Long    id;
    private String  name;
    private int     score;
    private Tattoo  tattoo;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tattoo getTattoo() {
        return tattoo;
    }

    public void setTattoo(Tattoo tattoo) {
        this.tattoo = tattoo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (score != player.score) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (tattoo != null ? !tattoo.equals(player.tattoo) : player.tattoo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + score;
        result = 31 * result + (tattoo != null ? tattoo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", tattoo=" + tattoo +
                '}';
    }
}
