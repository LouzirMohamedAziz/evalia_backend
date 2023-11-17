package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Country;
import com.evalia.backend.services.CountryService;
@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.createCountry(country);
    }

    @GetMapping
    public List<Country> getAllCatalogs() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCatalogById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCountryc(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}