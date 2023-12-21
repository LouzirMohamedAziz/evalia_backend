package com.evalia.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;

import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;

@Controller
public class MetadataController {

    private CountryRepository countryRepository;

    public List<Country> getAllCountries(){
		return StreamSupport.stream(countryRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}