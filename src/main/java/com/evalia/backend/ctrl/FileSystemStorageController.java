package com.evalia.backend.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.ctrl.services.StorageService;
import com.evalia.backend.exceptions.StorageException;
import com.evalia.backend.exceptions.StorageFileNotFoundException;

@Controller
public class FileSystemStorageController implements StorageService {

	
	private final Path uploadLocation;

	
	public FileSystemStorageController(
			@Value("${evalia.storage.upload.loacation}") Path uploadLocation) {
		this.uploadLocation = uploadLocation;
		init();
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(uploadLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void store(MultipartFile file) {

		if (file.isEmpty()) {
			throw new StorageException("Cannot store empty file!");
		}

		Path destinationFile = this.uploadLocation.resolve(Paths.get(file.getOriginalFilename())).normalize()
				.toAbsolutePath();
		if (!destinationFile.getParent()
				.equals(this.uploadLocation.toAbsolutePath())) {
			// This is a security check
			throw new StorageException("Cannot store file outside upload location.");
		}
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public Path load(String filename) {
		return uploadLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(Path file) {
		try {
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists() || resource.isReadable()) {
					return resource;
				} else {
					throw new StorageFileNotFoundException("Could not read file: " + file.getFileName());
				}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + file.getFileName(), e);
		}
	}

	@Override
	public boolean delete(String filename) {
		Path path = load(filename);
		try {
			return Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Could not delete file: " + filename, e);
		}
	}
}
