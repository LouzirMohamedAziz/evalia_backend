package com.evalia.backend.controllers;

import static com.evalia.backend.utils.specification.ProfessionalSpecification.*;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.evalia.backend.controllers.services.ProfessionalService;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Sector;
import com.evalia.backend.models.SubSector;
import com.evalia.backend.repositories.ProfessionalRepository;
import com.evalia.backend.utils.converters.DelegationConverter;
import com.evalia.backend.utils.converters.GovernorateConverter;
import com.evalia.backend.utils.converters.SectorConverter;
import com.evalia.backend.utils.converters.SubSectorConverter;

@Controller
public class ProfessionalController implements ProfessionalService{

	
	private final GovernorateConverter governorateConverter;
	private final DelegationConverter delegationConverter;
	private final SectorConverter sectorConverter;
	private final SubSectorConverter subSectorConverter;
	private final ProfessionalRepository professionalRepository;

	
    public ProfessionalController(GovernorateConverter governorateConverter,
    		DelegationConverter delegationConverter,
    		SectorConverter sectorConverter,
    		SubSectorConverter subSectorConverter,
    		ProfessionalRepository professionalRepository) {
    	this.governorateConverter = governorateConverter;
    	this.delegationConverter = delegationConverter;
    	this.sectorConverter = sectorConverter;
    	this.subSectorConverter = subSectorConverter;
        this.professionalRepository = professionalRepository;
    }

    
	@Override
	public List<Professional> search(Long gov, Long delg, String sec,
			String subSec) {
		
		Governorate governorate = Objects.nonNull(gov) ? 
				governorateConverter.convert(gov) : null;
		Delegation delegation = Objects.nonNull(delg) ?
				delegationConverter.convert(delg) : null;
		Sector sector = !StringUtils.isBlank(sec) ?
				sectorConverter.convert(sec) : null;
		SubSector subSector = !StringUtils.isBlank(subSec) ?
				subSectorConverter.convert(subSec) : null;
		
		Specification<Professional> filters = Specification
				.where(Objects.isNull(governorate) ? null : inGovernorate(governorate))
                .and(Objects.isNull(delegation) ? null : inDelegation(delegation))
                .and(Objects.isNull(sector) ? null : partOfSector(sector))
                .and(Objects.isNull(subSector) ? null : partOfSubSector(subSector));
		
		return professionalRepository.findAll(filters);
	}

}
