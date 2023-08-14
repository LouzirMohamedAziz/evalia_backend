package com.evalia.backend.util;

import org.springframework.data.repository.CrudRepository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InitializersUtils {

	public static <T, ID> boolean isLoaded(CrudRepository<T, ID> repository) {
		return repository.count() > 0;
	}
}
