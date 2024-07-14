package com.evalia.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Sector;
import com.evalia.backend.repositories.SectorRepository;

@Component
public class SectorConverter implements Converter<String, Sector> {
	
    private final SectorRepository sectorRepository;

    public SectorConverter(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Sector convert(String source) {
        return sectorRepository.findById(source).orElseThrow(() -> ResourceNotFoundException
                .build(Sector.class.getName(), String.valueOf(source)));
    }

}
