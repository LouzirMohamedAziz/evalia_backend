package com.evalia.backend.security.config;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.util.ResourceUtils;

@Configuration
public class RsaKeyProperties {
	
	@Value("${rsa.publicKey}")
	private String publicKeyPath;
	
	@Value("${rsa.privateKey}")
	private String privateKeyPath;
	
	
	@Bean
	public RSAPublicKey publicKey() throws ResourceNotFoundException, IOException {
		return ResourceUtils.loadPublicKey(publicKeyPath);
	}
	
	@Bean
	public RSAPrivateKey privateKey() throws ResourceNotFoundException, IOException {
		return ResourceUtils.loadPrivateKey(privateKeyPath);
	}
}
