package com.evalia.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Optional;

import com.evalia.backend.EvaliaApplication;
import com.evalia.backend.exceptions.ResourceNotFoundException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceUtils {

	public static String buildMessage(String template, Object... params) {
		return MessageFormat.format(template, params);
	}

	public static InputStream loadResource(String path) throws ResourceNotFoundException {
		InputStream stream = EvaliaApplication.class
				.getClassLoader()
				.getResourceAsStream(path);
		
		return Optional.ofNullable(stream)
				.orElseThrow(() -> ResourceNotFoundException.build(path));
	}

	public static String loadAsText(String path) throws IOException {
		InputStream stream = loadResource(path);
		StringBuilder builder = new StringBuilder();
		
		try (Reader reader = new BufferedReader(
				new InputStreamReader(stream, StandardCharsets.UTF_8))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				builder.append((char) c);
			}
		}
		return builder.toString();
	}
}
