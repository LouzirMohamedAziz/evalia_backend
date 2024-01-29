package com.evalia.backend.metadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface Enumeration{

	
	/**
	 * Return the alias associated to this {@code Enumeration}.
	 * 
	 * @return alias as {@code String}.
	 */
	public String getAlias();
	

	/**
	 * Gets all aliases from an array of {@code Enumeration}.
	 * 
	 * @param enums : the table of enums from where to extract the aliases.
	 * @return list of extracted aliases.
	 */
	public static List<String> getAliases(Enumeration... enums) {
		return Arrays.asList(enums).stream()
				.map(Enumeration::getAlias)
				.collect(Collectors.toList());
	}
	
	
	/**
	 * Gets all the {@code Enumeration} with their respected aliases.
	 * 
	 * @param enums : the table of enums from where to extract the aliases.
	 * @return map of {@code Enumerations} mapped to their aliases.
	 */
	public static Map<Enumeration, String> getValues(Enumeration... enums){
		return Arrays.asList(enums).stream()
				.collect(Collectors.toMap(Function.identity(), Enumeration::getAlias));
	}
}
