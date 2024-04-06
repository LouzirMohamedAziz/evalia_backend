package com.evalia.backend.controllers;

import com.evalia.backend.metadata.Performance;
import com.evalia.backend.models.Rating;
import com.evalia.backend.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    // Search Ratings by Professional ID and Date Range
    @GetMapping("/findByProfessionalIdAndDateRange")
    public List<Rating> findByProfessionalIdAndDateRange(@RequestParam Long professionalId,
                                                         @RequestParam Date startDate,
                                                         @RequestParam Date endDate) {
        return ratingRepository.findByProfessionalIdAndDateRange(professionalId, startDate, endDate);
    }

    // Search Ratings by Indicator Name
    @GetMapping("/findByIndicatorName")
    public List<Rating> findByIndicatorName(@RequestParam String indicatorName) {
        return ratingRepository.findByIndicatorName(indicatorName);
    }

    // Search Ratings by Rate Range
    @GetMapping("/findByRateRange")
    public List<Rating> findByRateRange(@RequestParam Double minRate,
                                        @RequestParam Double maxRate) {
        return ratingRepository.findByRateRange(minRate, maxRate);
    }

    // Search Ratings by Post ID
    @GetMapping("/findByPostId")
    public List<Rating> findByPostId(@RequestParam Long postId) {
        return ratingRepository.findByPostId(postId);
    }

    // Combination of Filters
    @GetMapping("/findByFilters")
    public List<Rating> findByFilters(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) Date startDate,
                                      @RequestParam(required = false) Date endDate,
                                      @RequestParam(required = false) Double minRate,
                                      @RequestParam(required = false) Double maxRate) {
        return ratingRepository.findByFilters(name, startDate, endDate, minRate, maxRate);
    }

    // Search Ratings with Sorting
    @GetMapping("/findAllOrderByDateDesc")
    public List<Rating> findAllOrderByDateDesc() {
        return ratingRepository.findAllOrderByDateDesc();
    }

    @GetMapping("/findAllOrderByRateDesc")
    public List<Rating> findAllOrderByRateDesc() {
        return ratingRepository.findAllOrderByRateDesc();
    }

    // Search Ratings by Professional Name
    @GetMapping("/findByProfessionalName")
    public List<Rating> findByProfessionalName(@RequestParam String professionalName) {
        return ratingRepository.findByProfessionalName(professionalName);
    }

    // Search Ratings by Indicator Performance
    @GetMapping("/findByIndicatorPerformance")
    public List<Rating> findByIndicatorPerformance(@RequestParam String performance) {
        // Assuming 'performance' parameter represents enum values of Performance
        return ratingRepository.findByIndicatorPerformance(Performance.valueOf(performance));
    }

    // Search Ratings by Civil Actor's Phone Number
    @GetMapping("/findByCivilActorPhoneNumber")
    public List<Rating> findByCivilActorPhoneNumber(@RequestParam String phoneNumber) {
        return ratingRepository.findByCivilActorPhoneNumber(phoneNumber);
    }

    // Search Ratings by Business Group Name
    @GetMapping("/findByBusinessGroupName")
    public List<Rating> findByBusinessGroupName(@RequestParam String businessGroupName) {
        return ratingRepository.findByBusinessGroupName(businessGroupName);
    }

    // Additional method examples
    // You can add additional endpoints for other custom methods defined in the repository as needed.

}
