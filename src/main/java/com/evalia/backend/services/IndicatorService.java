
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Indicator;
import com.evalia.backend.repositories.IndicatorRepository;

@Service
public class IndicatorService {

    private final IndicatorRepository indicatorRepository;

    @Autowired
    public IndicatorService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    public Indicator createIndicator(Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

    public List<Indicator> getAllIndicator() {
        return indicatorRepository.findAll();
    }

    public Indicator getIndicatorById(Long id) {
        return indicatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Indicator not found with id: " + id));
    }

    public void deleteIndicator(Long id) {
        indicatorRepository.deleteById(id);
    }
}