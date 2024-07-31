package com.evalia.backend.controllers.services;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void init();

	public void store(MultipartFile file);

	public Path load(String filename);

	public Resource loadAsResource(Path file);

	public boolean delete(String filename);

}