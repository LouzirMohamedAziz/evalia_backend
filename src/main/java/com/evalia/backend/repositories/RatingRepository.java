package com.evalia.backend.repositories;

import com.evalia.backend.metadata.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.evalia.backend.models.Rating;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.evalia.backend.models.Rating;
import java.util.Date;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Search Ratings by Professional ID and Date Range
    @Query("SELECT r FROM Rating r WHERE r.professional.name = :professionalName AND r.date BETWEEN :startDate AND :endDate")
    List<Rating> findByProfessionalNameAndDateRange(@Param("name") String professionalName,
                                                  @Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);

    // Search Ratings by Indicator Name
    @Query("SELECT r FROM Rating r JOIN r.indicator i WHERE i.name = :indicatorName")
    List<Rating> findByIndicatorName(@Param("indicatorName") String indicatorName);

    // Search Ratings by Rate Range
    @Query("SELECT r FROM Rating r WHERE r.rate BETWEEN :minRate AND :maxRate")
    List<Rating> findByRateRange(@Param("minRate") Double minRate,
                                 @Param("maxRate") Double maxRate);

    // Search Ratings by Post ID
    @Query("SELECT r FROM Rating r WHERE r.post.id = :postId")
    List<Rating> findByPostId(@Param("postId") Long postId);

    // Combination of Filters
    @Query("SELECT r FROM Rating r WHERE " +
           "(:professionalName IS NULL OR r.professional.name = :professionalName) AND " +
           "(:startDate IS NULL OR r.date >= :startDate) AND " +
           "(:endDate IS NULL OR r.date <= :endDate) AND " +
           "(:minRate IS NULL OR r.rate >= :minRate) AND " +
           "(:maxRate IS NULL OR r.rate <= :maxRate)")
    List<Rating> findByFilters(@Param("professionalName") String professionalName,
                               @Param("startDate") Date startDate,
                               @Param("endDate") Date endDate,
                               @Param("minRate") Double minRate,
                               @Param("maxRate") Double maxRate);

    // Search Ratings with Sorting
    @Query("SELECT r FROM Rating r ORDER BY r.date DESC")
    List<Rating> findAllOrderByDateDesc();

    @Query("SELECT r FROM Rating r ORDER BY r.rate DESC")
    List<Rating> findAllOrderByRateDesc();

    // Search Ratings by Professional Name
    @Query("SELECT r FROM Rating r JOIN r.professional p WHERE p.name = :professionalName")
    List<Rating> findByProfessionalName(@Param("professionalName") String professionalName);

    // Search Ratings by Indicator Performance
    @Query("SELECT r FROM Rating r JOIN r.indicator i WHERE i.performance = :performance")
    List<Rating> findByIndicatorPerformance(@Param("performance") Performance performance);

    // Search Ratings by Civil Actor's Phone Number
    @Query("SELECT r FROM Rating r JOIN r.professional p JOIN Civil c ON TYPE(p) = Civil AND c = p WHERE c.phone = :phoneNumber")
    List<Rating> findByCivilActorPhoneNumber(@Param("phoneNumber") String phoneNumber);

    // Search Ratings by Business Group Name
    @Query("SELECT r FROM Rating r JOIN r.professional p JOIN p.businessGroup bg WHERE bg.name = :businessGroupName")
    List<Rating> findByBusinessGroupName(@Param("businessGroupName") String businessGroupName);
    List<Rating> findByRateBetweenAndDateBetweenAndProfessionalNameAndIndicatorName(
            Double minRate, Double maxRate, Date startDate, Date endDate, String professionalName, String indicatorName);

    List<Rating> findByPostTitle(String postTitle);



}
