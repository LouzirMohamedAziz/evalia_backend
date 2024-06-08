package com.evalia.backend.rest.serializers;

import java.io.IOException;

import com.evalia.backend.models.Governorate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class GovernorateSerializer extends StdSerializer<Governorate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151734204333851335L;
	
	
	public GovernorateSerializer() {
		super(Governorate.class);
	}
	

	@Override
	public void serialize(Governorate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeNumber(value.getId());
	}
}
