package com.evalia.backend.rest.deserializers;

import java.io.IOException;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Country;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.repositories.DelegationRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DelegationDeserializer extends StdDeserializer<Delegation> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6956367101552729510L;
	
	private DelegationRepository delegationRepository;
	
	
	public DelegationDeserializer(DelegationRepository delegationRepository) {
		super(Delegation.class);
		this.delegationRepository = delegationRepository;
	}
	
	@Override
	public Class<?> handledType() {
		return Delegation.class;
	}


	@Override
	public Delegation deserialize(JsonParser parser, DeserializationContext ctxt) 
			throws IOException, JacksonException {
		
		JsonNode node = parser.getCodec().readTree(parser);

		long delegationCode = node.longValue();

		return delegationRepository.findById(delegationCode)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Delegation.class.getName(), String.valueOf(delegationCode)));
	}
}
