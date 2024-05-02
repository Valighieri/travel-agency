package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.projections.CountryCountDto;
import com.calaton.travelagency.model.dto.projections.TourSumAvgDto;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    // Task: "Most popular destination in a given year"
    @Query(value = "select new com.calaton.travelagency.model.dto.projections.CountryCountDto(" +
            "t.destination, count(c)) from Tour t, in(t.clients) c where year(t.departureDate) = :year " +
            "group by t.destination order by count(c) desc")
    List<CountryCountDto> findDestinationsOrderedByPopularity(Integer year, Limit limit); // Limit limit


    // Task: "The average price and total amount for which a tour was sold"
    @Query(value = "select new com.calaton.travelagency.model.dto.projections.TourSumAvgDto" +
            "(t, sum(t.initialPrice - t.initialPrice / 100 * d), avg(t.initialPrice - t.initialPrice / 100 * d))" +
            " from Tour t, in(t.discounts) d where t.id = :tourId group by t")
    Optional<TourSumAvgDto> findTourAvgPriceAndTotalAmount(Long tourId);

    // Task: "The most popular trip (top 3) with the lowest selling price"
    @Query("select t from Tour t, in(t.clients) c " +
            "group by t.id order by count(c.id) desc")
    List<Tour> findToursOrderedByPopularity(Limit limit);

    @Query(value = "select t from Tour t where t.guide.id = :guideId and t.returnDate >= current_date")
    List<Tour> findGuideActualTours(Long guideId);

}
