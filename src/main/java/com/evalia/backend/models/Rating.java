package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.querydsl.core.annotations.QueryInit;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double rate;

    @NotNull
    private Date date;

    @Column(length = 500)
    private String feedback;
    
    private String attachement;

    @OneToOne
    private Civil evaluater;
    
    @QueryInit({"subSector.*", "address.*"})
    @ManyToOne(optional = false)
    private Professional evaluatee;

    @ManyToOne
    private Indicator indicator;
}
