package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Integer> {
}
