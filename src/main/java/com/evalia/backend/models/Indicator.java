package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indicatorId;
    private String indicatorName;

    private Performance performance;


    public Indicator() {
    }

    public Indicator(Long indicatorId, String indicatorName, Performance performance) {
        this.indicatorId = indicatorId;
        this.indicatorName = indicatorName;
        this.performance = performance;
    }

    public Long getIndicatorId() {
        return this.indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorName() {
        return this.indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Performance getPerformance() {
        return this.performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Indicator indicatorId(Long indicatorId) {
        setIndicatorId(indicatorId);
        return this;
    }

    public Indicator indicatorName(String indicatorName) {
        setIndicatorName(indicatorName);
        return this;
    }

    public Indicator performance(Performance performance) {
        setPerformance(performance);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Indicator)) {
            return false;
        }
        Indicator indicator = (Indicator) o;
        return Objects.equals(indicatorId, indicator.indicatorId) && Objects.equals(indicatorName, indicator.indicatorName) && Objects.equals(performance, indicator.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicatorId, indicatorName, performance);
    }

    @Override
    public String toString() {
        return "{" +
            " indicatorId='" + getIndicatorId() + "'" +
            ", indicatorName='" + getIndicatorName() + "'" +
            ", performance='" + getPerformance() + "'" +
            "}";
    }
    
}
