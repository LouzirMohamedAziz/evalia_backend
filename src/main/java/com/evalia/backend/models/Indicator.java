package com.evalia.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.evalia.backend.metadata.Performance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter


@Entity
public class Indicator {

    @EqualsAndHashCode.Include
    @Id
    private String name;
    
    private Performance performance;

    @ManyToMany
    private List<SubSector> subSectors;
}