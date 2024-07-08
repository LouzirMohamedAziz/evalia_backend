package com.evalia.backend.rest.deserializers;

import java.io.IOException;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Country;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.GovernorateRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class GovernorateDeserializer extends StdDeserializer<Governorate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 111936086198978760L;
	
	
	private GovernorateRepository governorateRepository;

	
	public GovernorateDeserializer(GovernorateRepository governorateRepository) {
		super(Governorate.class);
		this.governorateRepository = governorateRepository;
	}
	
	@Override
	public Class<?> handledType() {
		return Governorate.class;
	}

	@Override
	public Governorate deserialize(JsonParser parser, DeserializationContext ctxt) 
			throws IOException, JacksonException {
		
		JsonNode node = parser.getCodec().readTree(parser);

		long governorateCode = node.longValue();

		return governorateRepository.findById(governorateCode)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Country.class.getName(), String.valueOf(governorateCode)));
	}
}
