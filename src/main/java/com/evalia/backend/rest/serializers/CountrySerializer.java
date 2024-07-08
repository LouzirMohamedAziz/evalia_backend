package com.evalia.backend.rest.serializers;

import java.io.IOException;

import com.evalia.backend.models.Country;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CountrySerializer extends StdSerializer<Country> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151734204333851335L;

	
	public CountrySerializer() {
		super(Country.class);
	}
	

	@Override
	public void serialize(Country value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.getIsoCode());
	}
}
