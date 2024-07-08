package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.evalia.backend.metadata.Performance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class Indicator {

    @EqualsAndHashCode.Include
    @Id
    private String name;
    
    private Performance performance;
}
