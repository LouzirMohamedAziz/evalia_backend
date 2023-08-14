package com.evalia.backend.initializers;

import org.springframework.boot.CommandLineRunner;

public abstract class Initializer<T> implements CommandLineRunner{
	
	abstract void preLoad(Class<T> clazz, String filePath);
}
