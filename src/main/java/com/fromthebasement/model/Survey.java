package com.fromthebasement.model;

import javax.persistence.*;

/**
 * Created by jeffginn on 4/13/14.
 */
@Entity
public class Survey extends BaseObject {
    private Long id;
    private String name;
    private String slogan;
    private String imageUrl;

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

    @Column(length=200)
    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (imageUrl != null ? !imageUrl.equals(survey.imageUrl) : survey.imageUrl != null) return false;
        if (name != null ? !name.equals(survey.name) : survey.name != null) return false;
        if (slogan != null ? !slogan.equals(survey.slogan) : survey.slogan != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (slogan != null ? slogan.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
