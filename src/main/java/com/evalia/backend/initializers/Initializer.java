package com.evalia.backend.initializers;

import org.springframework.boot.CommandLineRunner;

import com.evalia.backend.exceptions.InitializationException;

/**
 * Bean that executes an action before application startup.
 * 
 * @author Hamdi Jandoubi
 *
 */
public interface Initializer extends CommandLineRunner{
	
	/**
	 * Initialization method for loading predefined objects.
	 * 
	 * @throws Exception
	 */
	public void initialize() throws InitializationException;
	
	
	/**
	 * To determine whether this {@code Initializer} 
	 * should run on startup or not.
	 * 
	 * @return {@code true} if this {@code Initializer} should run, {@code false} otherwise.
	 * @throws Exception
	 */
	public boolean isInitialized() throws InitializationException;
	
	
	/**
	 * The {@code CommandLineRunner}'s entry point.
	 * 
	 * @throws Exception
	 */
	public default void run(String... args) throws InitializationException {
		if(!isInitialized()) {
			initialize();
		}
	}
}
 