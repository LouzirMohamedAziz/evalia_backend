package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ratingId;
    private String ratingComment;
    private String score;
    // private User rater;



    public Rating() {
    }

    public Rating(String ratingId, String ratingComment, String score) {
        this.ratingId = ratingId;
        this.ratingComment = ratingComment;
        this.score = score;
    }

    public String getRatingId() {
        return this.ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingComment() {
        return this.ratingComment;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Rating ratingId(String ratingId) {
        setRatingId(ratingId);
        return this;
    }

    public Rating ratingComment(String ratingComment) {
        setRatingComment(ratingComment);
        return this;
    }

    public Rating score(String score) {
        setScore(score);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rating)) {
            return false;
        }
        Rating rating = (Rating) o;
        return Objects.equals(ratingId, rating.ratingId) && Objects.equals(ratingComment, rating.ratingComment) && Objects.equals(score, rating.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, ratingComment, score);
    }

    @Override
    public String toString() {
        return "{" +
            " ratingId='" + getRatingId() + "'" +
            ", ratingComment='" + getRatingComment() + "'" +
            ", score='" + getScore() + "'" +
            "}";
    }
    
}
