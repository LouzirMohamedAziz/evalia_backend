package com.evalia.backend.rest.deserializers;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.evalia.backend.models.Address;
import com.evalia.backend.models.Country;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.CountryRepository;
import com.evalia.backend.util.Constants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class AddressDeserializer extends JsonDeserializer<Address> {

    // Attributes
    private static final String COUNTRY = "country";
    private static final String GOVERNORATE = "governorate";
    private static final String DELEGATION = "delegation";
    private static final String STREET = "street";
    private static final String POSTAL_CODE = "postalCode";


    @Autowired
    private CountryRepository countryRepository;

    @Override
	public Address deserialize(JsonParser parser, DeserializationContext ctxt) 
    throws IOException {

        JsonNode node = parser.getCodec().readTree(parser);
        Address address = new Address();
		String countryCode = node.get(COUNTRY).textValue();
        String governorateCode = node.get(GOVERNORATE).textValue();
        String delegationCode = node.get(DELEGATION).textValue();
        String street = node.get(STREET).textValue();
        Integer postalCode = Integer.parseInt(node.get(POSTAL_CODE).textValue());

        Country country = countryRepository.findById(countryCode).orElseThrow(()->{
            return new NoSuchElementException(Constants.INVALID_ADDRESS);
        });

        
        Optional<Governorate> optionalGovernorate = country.getGovernorates()
            .stream()
            .filter((g)->{
                return g.getName().equals(governorateCode);
            })
            .findFirst();
            
        Governorate governorate = optionalGovernorate.orElseThrow(()->{
            return new NoSuchElementException(Constants.INVALID_ADDRESS);
        });

        Optional<Delegation> optionalDelegation = governorate.getDelegations()
            .stream()
            .filter((d)->{
                return d.getName().equals(delegationCode);
            })
            .findFirst();
            
        Delegation delegations = optionalDelegation.orElseThrow(()->{
            return new NoSuchElementException(Constants.INVALID_ADDRESS);
        });

        address.setCountry(country);
        address.setGovernorate(governorate);
        address.setDelegation(delegations);
        address.setStreet(street);
        address.setPostalCode(postalCode);

        return address;
	}

}