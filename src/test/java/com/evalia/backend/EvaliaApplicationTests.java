package com.evalia.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class EvaliaApplicationTests {

	@Test
	public static void mapwithorder() {
		String key = "name";
		String value = "Hemden";
		String key2 = "age";
		String value2 = "20";
		String key3 = "occupation";
		String value3 = "Developper";

		Map<String, String> map = new TreeMap<>();
		map.put(key, value);
		map.put(key2, value2);
		map.put(key3, value3);


		System.out.println(map);
	}

	public static void main(String[] args) {
		mapwithorder();
	}

}
