package com.evalia.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;

import com.evalia.backend.metadata.Performance;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;

@Controller
public class MetadataController {

  private CountryRepository countryRepository;

  // TODO: get searchable actor types by current user type
  public List<String> getActorTypesByCurrentSession() {
    return null;
  }

  // TODO: get performance possible values
  public List<Performance> getPerformancePossibelValues() {
    return null;
  }
}