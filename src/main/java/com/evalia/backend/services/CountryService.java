package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;

public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}