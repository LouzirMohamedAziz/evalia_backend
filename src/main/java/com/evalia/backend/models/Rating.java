package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Rating {

    @Id
    private Long id;

    private Double rate;

    private Date date;

    @ManyToOne
    Professional professional;

    
    @OneToOne
    private Post post;

    @ManyToOne
    private Indicator indicator;
}