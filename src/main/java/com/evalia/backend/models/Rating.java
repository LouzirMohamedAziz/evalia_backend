package com.evalia.backend.models;

import java.util.Date;

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
}