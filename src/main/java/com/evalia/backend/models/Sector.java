package com.evalia.backend.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sectorId;
    
    private String sectorName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SubSector> subSectors;

    public Sector() {
    }

    public Sector(String sectorId, String sectorName, List<SubSector> subSectors) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.subSectors = subSectors;
    }

    public String getSectorId() {
        return this.sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return this.sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public List<SubSector> getSubSectors() {
        return this.subSectors;
    }

    public void setSubSectors(List<SubSector> subSectors) {
        this.subSectors = subSectors;
    }

    public Sector sectorId(String sectorId) {
        setSectorId(sectorId);
        return this;
    }

    public Sector sectorName(String sectorName) {
        setSectorName(sectorName);
        return this;
    }

    public Sector subSectors(List<SubSector> subSectors) {
        setSubSectors(subSectors);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sector)) {
            return false;
        }
        Sector sector = (Sector) o;
        return Objects.equals(sectorId, sector.sectorId) && Objects.equals(sectorName, sector.sectorName) && Objects.equals(subSectors, sector.subSectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectorId, sectorName, subSectors);
    }

    @Override
    public String toString() {
        return "{" +
            " sectorId='" + getSectorId() + "'" +
            ", sectorName='" + getSectorName() + "'" +
            ", subSectors='" + getSubSectors() + "'" +
            "}";
    }

    

}
