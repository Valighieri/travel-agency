package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query(value = "select t from Tour t where t.guide.id = :guideId and t.returnDate >= current_date")
    List<Tour> findGuideActualTours(Long guideId);

}
