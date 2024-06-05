package com.evalia.backend.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Rating {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private Double rate;

    private Date date;

    @OneToOne(optional = false)
    private Post post;

    @ManyToOne(optional = false)
    Professional professional;

    @ManyToOne
    private Indicator indicator;

    // public static class RatingDSL {
    //     // Method chaining DSL for accessing and manipulating Rating attributes
    //     public static RatingDSL rating() {
    //         return new RatingDSL(new Rating());
    //     }

    //     private Rating rating;

    //     public RatingDSL(Rating rating) {
    //         this.rating = rating;
    //     }

    //     public RatingDSL id(Long id) {
    //         rating.setId(id);
    //         return this;
    //     }

    //     public RatingDSL rate(Double rate) {
    //         rating.setRate(rate);
    //         return this;
    //     }

    //     public RatingDSL date(Date date) {
    //         rating.setDate(date);
    //         return this;
    //     }

    //     public RatingDSL post(Post post) {
    //         rating.setPost(post);
    //         return this;
    //     }

    //     public RatingDSL professional(Professional professional) {
    //         rating.setProfessional(professional);
    //         return this;
    //     }

    //     public RatingDSL indicator(Indicator indicator) {
    //         rating.setIndicator(indicator);
    //         return this;
    //     }

    //     public Rating build() {
    //         // You can add additional validation or processing logic here if needed
    //         return rating;
    //     }
    // }
}
