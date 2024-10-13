package com.evalia.backend.utils.specification;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.evalia.backend.models.Address;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Ratable;
import com.evalia.backend.models.Sector;
import com.evalia.backend.models.SubSector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class RatableSpecification {

	
	public static Specification<Ratable> inGovernorate(Governorate governorate){
		return (root, query, builder) -> {
			Join<Ratable, Address> ratablesInGov = root.join("address");
            return builder.equal(ratablesInGov.get("governorate"), governorate);
		};
	}
	
	public static Specification<Ratable> inDelegation(Delegation delegation){
		return (root, query, builder) -> {
			Join<Professional, Address> ratablesInDel = root.join("address");
            return builder.equal(ratablesInDel.get("delegation"), delegation);
		};
	}
	
	public static Specification<Ratable> partOfSector(Sector sector){
		return (root, query, builder) -> {
			Join<Professional, SubSector> ratablesOf = root.join("subSector");
            return builder.equal(ratablesOf.get("sector"), sector);
		};
	}
	
	public static Specification<Ratable> partOfSubSector(SubSector subSector){
		return (root, query, builder) -> 
            builder.equal(root.get("subSector"), subSector);
	}
}
