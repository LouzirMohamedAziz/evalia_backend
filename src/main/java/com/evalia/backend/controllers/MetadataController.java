package com.evalia.backend.controllers;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.evalia.backend.metadata.ActorType;
import com.evalia.backend.metadata.Enumeration;
import com.evalia.backend.metadata.Performance;
import com.evalia.backend.models.Account;

@Controller
public class MetadataController {
	

	public Map<Enumeration, String> getRatableActors() {

		Map<Enumeration, String> values = null;
		Account currentAccount = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (currentAccount.getActor().getActoryType().equals(ActorType.CIVIL)) {
			values = Enumeration.getValues(ActorType.PUBLIC, ActorType.PRIVATE);
		} else {
			values = Enumeration.getValues(ActorType.PUBLIC, ActorType.PRIVATE, ActorType.PARTNERSHIP);
		}

		return values;
	}

	
	public Map<Enumeration, String> getPerformances() {
		return Enumeration.getValues(Performance.values());
	}

}