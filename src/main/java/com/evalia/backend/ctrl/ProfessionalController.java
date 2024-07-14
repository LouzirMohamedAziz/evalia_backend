package com.evalia.backend.ctrl;

import static com.evalia.backend.util.specification.ProfessionalSpecification.inDelegation;
import static com.evalia.backend.util.specification.ProfessionalSpecification.inGovernorate;
import static com.evalia.backend.util.specification.ProfessionalSpecification.partOfSector;
import static com.evalia.backend.util.specification.ProfessionalSpecification.partOfSubSector;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.evalia.backend.converters.DelegationConverter;
import com.evalia.backend.converters.GovernorateConverter;
import com.evalia.backend.converters.SectorConverter;
import com.evalia.backend.converters.SubSectorConverter;
import com.evalia.backend.ctrl.services.ProfessionalService;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Sector;
import com.evalia.backend.models.SubSector;
import com.evalia.backend.repositories.ProfessionalRepository;

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
