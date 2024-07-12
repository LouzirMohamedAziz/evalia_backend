package com.evalia.backend.util.specification;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.evalia.backend.models.Governorate;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Sector;
import com.evalia.backend.models.Address;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.SubSector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class ProfessionalSpecification {

	
	public static Specification<Professional> inGovernorate(Governorate governorate){
		return (root, query, builder) -> {
			Join<Professional, Address> professionalsInGov = root.join("address");
            return builder.equal(professionalsInGov.get("governorate"), governorate);
		};
	}
	
	public static Specification<Professional> inDelegation(Delegation delegation){
		return (root, query, builder) -> {
			Join<Professional, Address> professionalsInDel = root.join("address");
            return builder.equal(professionalsInDel.get("delegation"), delegation);
		};
	}
	
	public static Specification<Professional> partOfSector(Sector sector){
		return (root, query, builder) -> {
			Join<Professional, SubSector> professionalsOf = root.join("subSector");
            return builder.equal(professionalsOf.get("sector"), sector);
		};
	}
	
	public static Specification<Professional> partOfSubSector(SubSector subSector){
		return (root, query, builder) -> 
            builder.equal(root.get("subSector"), subSector);
	}
}
