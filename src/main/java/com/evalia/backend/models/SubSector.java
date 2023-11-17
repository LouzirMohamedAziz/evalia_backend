package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class SubSector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String subSectorId;
    
    private String subSectorName;


    public SubSector() {
    }

    public SubSector(String subSectorId, String subSectorName) {
        this.subSectorId = subSectorId;
        this.subSectorName = subSectorName;
    }

    public String getSubSectorId() {
        return this.subSectorId;
    }

    public void setSubSectorId(String subSectorId) {
        this.subSectorId = subSectorId;
    }

    public String getSubSectorName() {
        return this.subSectorName;
    }

    public void setSubSectorName(String subSectorName) {
        this.subSectorName = subSectorName;
    }

    public SubSector subSectorId(String subSectorId) {
        setSubSectorId(subSectorId);
        return this;
    }

    public SubSector subSectorName(String subSectorName) {
        setSubSectorName(subSectorName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SubSector)) {
            return false;
        }
        SubSector subSector = (SubSector) o;
        return Objects.equals(subSectorId, subSector.subSectorId) && Objects.equals(subSectorName, subSector.subSectorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subSectorId, subSectorName);
    }

    @Override
    public String toString() {
        return "{" +
            " subSectorId='" + getSubSectorId() + "'" +
            ", subSectorName='" + getSubSectorName() + "'" +
            "}";
    }
    
}
