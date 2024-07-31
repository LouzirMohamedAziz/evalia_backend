package com.evalia.backend.controllers;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.controllers.services.RatingController;
import com.evalia.backend.controllers.services.StorageService;
import com.evalia.backend.exceptions.InvalidEvaluaterException;
import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.exceptions.StorageFileNotFoundException;
import com.evalia.backend.models.Actor;
import com.evalia.backend.models.Civil;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Rating;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.RatingRepository;
import com.evalia.backend.utils.dsl.query.QslRatingFetcher;

@Controller
public class RatingControllerImpl implements RatingController {
	
	private StorageService storageService;
	private AccountRepository accountRepository;
	private RatingRepository ratingRepository;
	private QslRatingFetcher ratingFetcher;
	
	
	public RatingControllerImpl(StorageService storageService,
			QslRatingFetcher ratingFetcher,
			AccountRepository accountRepository,
			RatingRepository ratingRepository) {
		this.storageService = storageService;
		this.ratingFetcher = ratingFetcher;
		this.accountRepository = accountRepository;
		this.ratingRepository = ratingRepository;
	}
	

	@Override
	public Double avg(Map<String, String> criterions) {
		return ratingFetcher.avg(criterions);
	}
	
	@Override
	public List<Rating> search(Pageable pageable, Order order, Map<String, String> criterions) {
		return ratingFetcher.fetch(pageable, order, criterions);
	}

	@Override
	public Rating add(Rating rating) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Actor evaluater = accountRepository.findById(username).orElseThrow().getActor();
		if(evaluater instanceof Professional) {
			throw InvalidEvaluaterException.build(Professional.class.getTypeName());
		}
		rating.setDate(new Date());
		rating.setEvaluater((Civil)evaluater);
		if (rating.getRate() == null) {
            throw new IllegalArgumentException("Rate must not be null");
        }
		return ratingRepository.save(rating);
	}


	@Override
	public void edit(Rating rating) {
		add(rating);
	}


	@Override
	public void delete(Long id) {
		ratingRepository.deleteById(id);
	}


	@Override
	public void attach(Long id, MultipartFile file) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Rating.class.getName(), String.valueOf(id)));
		Path path = storageService.load(file.getOriginalFilename());
		storageService.store(file);
		rating.setAttachement(path.toUri().toString());
		ratingRepository.save(rating);
	}


	@Override
	public Resource getAttachment(Long id) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Rating.class.getName(), String.valueOf(id)));
		String uri = rating.getAttachement();
		if(StringUtils.isBlank(uri)) {
			throw new StorageFileNotFoundException("Rating does not have an attachment");
		}
		Path file = Paths.get(URI.create(uri));
		return storageService.loadAsResource(file);
	}
}
