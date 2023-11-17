package com.evalia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.evalia.backend")
@EnableJpaRepositories(basePackages = "com.evalia.backend.repositories")
@EntityScan(basePackages = "com.evalia.backend.models")
@SpringBootApplication
public class EvaliaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EvaliaApplication.class, args);
	}
}