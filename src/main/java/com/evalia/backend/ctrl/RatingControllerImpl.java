package com.evalia.backend.ctrl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.evalia.backend.ctrl.services.RatingController;
import com.evalia.backend.dsl.query.QslRatingFetcher;
import com.evalia.backend.exceptions.InvalidEvaluaterException;
import com.evalia.backend.models.Actor;
import com.evalia.backend.models.Civil;
import com.evalia.backend.models.Professional;
import com.evalia.backend.models.Rating;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.RatingRepository;

@Controller
public class RatingControllerImpl implements RatingController {
	
	private AccountRepository accountRepository;
	private RatingRepository ratingRepository;
	private QslRatingFetcher ratingFetcher;
	
	
	public RatingControllerImpl(QslRatingFetcher ratingFetcher,
			AccountRepository accountRepository,
			RatingRepository ratingRepository) {
		this.ratingFetcher = ratingFetcher;
		this.accountRepository = accountRepository;
		this.ratingRepository = ratingRepository;
	}
	

	@Override
	public List<Rating> search(Map<String, String> criterions) {
		return ratingFetcher.fetch(criterions);
	}


	@Override
	public void add(Rating rating) {
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
		ratingRepository.save(rating);
	}


	@Override
	public void edit(Rating rating) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
