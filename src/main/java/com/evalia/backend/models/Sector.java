package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hamdi Jandoubi
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Sector {

	@EqualsAndHashCode.Include
	@Id
	private String name;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
	private List<SubSector> subSectors;
	
	public void addSubSector(SubSector subSector) {
        if(Objects.nonNull(subSector.getSector())) {
            subSector.getSector().removeSubSector(subSector);
        }
        subSector.setSector(this);
        subSectors.add(subSector);
    }


    public void removeSubSector(SubSector subSector) {
        if(Objects.nonNull(subSector)) {
            subSectors.remove(subSector);
        }
    }


    public void setSubSectors(List<SubSector> subSectors) {
        if(Objects.isNull(this.subSectors)) {
            this.subSectors = new ArrayList<SubSector>();
        }
        this.subSectors.clear();
        subSectors.forEach(this::addSubSector);
    }
}
