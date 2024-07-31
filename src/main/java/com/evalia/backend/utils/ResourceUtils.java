package com.evalia.backend.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Optional;

import com.evalia.backend.EvaliaApplication;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceUtils {
	
	public static final String MSG_FILENOTFOUND = "The file {0} was not found on the classpath!";

	public static String buildMessage(String template, Object... params) {
		return MessageFormat.format(template, params);
	}

	public static InputStream loadResource(String path) throws FileNotFoundException {
		InputStream stream = EvaliaApplication.class
				.getClassLoader()
				.getResourceAsStream(path);
		
		return Optional.ofNullable(stream)
				.orElseThrow(() -> {
					String msg = buildMessage(MSG_FILENOTFOUND, path);
					return new FileNotFoundException(msg);
				});
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
