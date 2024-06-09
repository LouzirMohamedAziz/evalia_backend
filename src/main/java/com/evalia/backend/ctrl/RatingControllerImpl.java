package com.evalia.backend.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.evalia.backend.ctrl.services.RatingController;
import com.evalia.backend.dsl.query.QslRatingFetcher;
import com.evalia.backend.models.Rating;

@Controller
public class RatingControllerImpl implements RatingController {
	
	private QslRatingFetcher ratingFetcher;
	
	
	public RatingControllerImpl(QslRatingFetcher ratingFetcher) {
		this.ratingFetcher = ratingFetcher;
	}
	

	@Override
	public List<Rating> search(Map<String, String> criterions) {
		return ratingFetcher.fetch(criterions);
	}

}
