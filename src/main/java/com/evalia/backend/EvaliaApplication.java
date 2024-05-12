package com.evalia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ImportResource("classpath:spring-security.xml")
public class EvaliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaliaApplication.class, args);
	}
}
