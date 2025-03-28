package com.evalia.backend.utils.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.SubSector;
import com.evalia.backend.repositories.SubSectorRepository;

@Component
public class SubSectorConverter implements Converter<String,SubSector> {
	
    private final SubSectorRepository subSectorRepository;

    public SubSectorConverter(SubSectorRepository subSectorRepository){
        this.subSectorRepository = subSectorRepository;
    }

    @Override
    @Nullable
    public SubSector convert(String source) {
        return subSectorRepository.findById(source).orElseThrow(() -> ResourceNotFoundException
                .build(SubSector.class.getName(), String.valueOf(source)));
    }


}
