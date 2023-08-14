package com.evalia.backend.initializers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.evalia.backend.models.ERole;
import com.evalia.backend.models.Role;
import com.evalia.backend.repositories.RoleRepository;
import com.evalia.backend.util.InitializersUtils;

@Component
@Order(0)
public class RolesInitializer extends Initializer<Role>{
	
	@Autowired
	RoleRepository repository;
	
	private List<Role> getRoles(){
		return Arrays.asList(ERole.values()).stream()
				.map(eRole -> new Role(eRole))
				.collect(Collectors.toList());
	}

	@Override
	void preLoad(Class<Role> clazz, String filePath) {
		repository.saveAll(getRoles());
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if(!InitializersUtils.isLoaded(repository)) {
			preLoad(null, null);
		}
	}
}
