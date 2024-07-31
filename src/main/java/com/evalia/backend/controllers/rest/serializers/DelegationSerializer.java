package com.evalia.backend.controllers.rest.serializers;

import java.io.IOException;

import com.evalia.backend.models.Delegation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DelegationSerializer extends StdSerializer<Delegation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151734204333851335L;
	
	
	public DelegationSerializer() {
		super(Delegation.class);
	}
	

	@Override
	public void serialize(Delegation value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeNumber(value.getId());
	}
}
