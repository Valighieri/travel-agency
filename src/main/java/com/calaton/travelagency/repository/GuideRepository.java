package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {

    // Task: "Which tour guide generated the highest revenue, i.e., handled tours for the
    //      highest total in a given year."
    @Query(value = "select t.guide from Tour t, in(t.discounts) d where year(t.departureDate) = :year" +
            " group by t.guide order by sum(t.initialPrice - t.initialPrice / 100 * d) desc limit 1")
    Optional<Guide> findGuidesOrderedByRevenue(Integer year);

}
