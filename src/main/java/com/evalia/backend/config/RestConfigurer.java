package com.evalia.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evalia.backend.models.Country;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.CountryRepository;
import com.evalia.backend.repositories.DelegationRepository;
import com.evalia.backend.repositories.GovernorateRepository;
import com.evalia.backend.rest.deserializers.CountryDeserializer;
import com.evalia.backend.rest.deserializers.DelegationDeserializer;
import com.evalia.backend.rest.deserializers.GovernorateDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class RestConfigurer {
	
	private final CountryRepository countryRepository;
	private final GovernorateRepository governorateRepository;
	private final DelegationRepository delegationRepository;
	
	public RestConfigurer(CountryRepository countryRepository,
			GovernorateRepository governorateRepository,
			DelegationRepository delegationRepository) {
		
		this.countryRepository = countryRepository;
		this.governorateRepository = governorateRepository;
		this.delegationRepository = delegationRepository;
	}
	
//	@Override
//	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
//		config.useHalAsDefaultJsonMediaType(false);
//	}
//
//	@Override
//	public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
//		SimpleModule simpleModule = new SimpleModule();
//		
//		simpleModule.addDeserializer(Country.class, 
//				new CountryDeserializer(countryRepository));
//		simpleModule.addDeserializer(Governorate.class, 
//				new GovernorateDeserializer(governorateRepository));
//		simpleModule.addDeserializer(Delegation.class, 
//				new DelegationDeserializer(delegationRepository));
//		
//		objectMapper.registerModule(simpleModule);
//	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		
		simpleModule.addDeserializer(Country.class, 
				new CountryDeserializer(countryRepository));
		simpleModule.addDeserializer(Governorate.class, 
				new GovernorateDeserializer(governorateRepository));
		simpleModule.addDeserializer(Delegation.class, 
				new DelegationDeserializer(delegationRepository));
		
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}
	
}
