package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Indicator;
import com.evalia.backend.services.IndicatorService;
@RestController
@RequestMapping("/indicators")
public class IndicatorController {

    private final IndicatorService indicatorService;

    @Autowired
    public IndicatorController(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @PostMapping
    public Indicator createIndicator(@RequestBody Indicator indicator) {
        return indicatorService.createIndicator(indicator);
    }

    @GetMapping
    public List<Indicator> getAllIndicator() {
        return indicatorService.getAllIndicator();
    }

    @GetMapping("/{id}")
    public Indicator getIndicatornById(@PathVariable Long id) {
        return indicatorService.getIndicatorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIndicatorc(@PathVariable Long id) {
        indicatorService.deleteIndicator(id);
    }
}