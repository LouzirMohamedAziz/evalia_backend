package com.evalia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.evalia.backend")
public class EvaliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaliaApplication.class, args);
	}
}