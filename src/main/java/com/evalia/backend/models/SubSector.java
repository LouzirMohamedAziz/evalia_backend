package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubSector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String subSectorId;
    private String subSectorName;

    @OneToMany
    private SubSector subSector;

}
