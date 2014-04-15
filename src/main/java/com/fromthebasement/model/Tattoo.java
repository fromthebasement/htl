package com.fromthebasement.model;

/**
 * Created by jeffginn on 4/14/14.
 */

import javax.persistence.*;

/**
 * Custom branding (imageUrl, slogan)
 */
@Embeddable
public class Tattoo {
    private String imageUrl;
    private String slogan;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(length=200)
    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tattoo tattoo = (Tattoo) o;

        if (imageUrl != null ? !imageUrl.equals(tattoo.imageUrl) : tattoo.imageUrl != null) return false;
        if (slogan != null ? !slogan.equals(tattoo.slogan) : tattoo.slogan != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageUrl != null ? imageUrl.hashCode() : 0;
        result = 31 * result + (slogan != null ? slogan.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tattoo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}
