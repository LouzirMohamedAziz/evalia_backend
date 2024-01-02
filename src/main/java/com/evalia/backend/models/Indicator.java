package com.evalia.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.evalia.backend.metadata.Performance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
public class Indicator {


    
    @Id
    private String name;
    
    private Performance performance;

    @OneToMany
    private List<Rating> ratings; 
    
}