package com.evalia.backend.rest.deserializers;

import java.io.IOException;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public class CountryDeserializer extends StdDeserializer<Country> {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3189542143638500165L;
	
	private CountryRepository countryRepository;
	
    
    public CountryDeserializer(CountryRepository countryRepository) {
		super((Class<?>)null);
		this.countryRepository = countryRepository;
	}

    
    @Override
	public Class<?> handledType() {
		return Country.class;
	}


	@Override
	public Country deserialize(JsonParser parser, DeserializationContext ctxt) 
			throws IOException, JacksonException {

        JsonNode node = parser.getCodec().readTree(parser);
        
		String countryCode = node.textValue();

        return countryRepository.findById(countryCode)
        		.orElseThrow(() -> ResourceNotFoundException
        				.build(Country.class.getName(), countryCode));
	}
}
