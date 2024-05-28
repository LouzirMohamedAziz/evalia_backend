package com.evalia.backend.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.evalia.backend.repositories.CountryRepository;
import com.evalia.backend.repositories.DelegationRepository;
import com.evalia.backend.repositories.GovernorateRepository;
import com.evalia.backend.rest.deserializers.CountryDeserializer;
import com.evalia.backend.rest.deserializers.DelegationDeserializer;
import com.evalia.backend.rest.deserializers.GovernorateDeserializer;

@Configuration
public class RestConfigurer implements Jackson2ObjectMapperBuilderCustomizer {

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

//	@Bean
//	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//		SimpleModule simpleModule = new SimpleModule();
//
//		simpleModule.addDeserializer(Country.class, new CountryDeserializer(countryRepository));
//		simpleModule.addDeserializer(Governorate.class, new GovernorateDeserializer(governorateRepository));
//		simpleModule.addDeserializer(Delegation.class, new DelegationDeserializer(delegationRepository));
//
//		objectMapper.registerModule(simpleModule);
//	}

	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

		jacksonObjectMapperBuilder.deserializers(
				new CountryDeserializer(countryRepository),
				new GovernorateDeserializer(governorateRepository),
				new DelegationDeserializer(delegationRepository)
		);
	}

}
