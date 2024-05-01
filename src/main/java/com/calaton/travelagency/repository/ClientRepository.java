package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "select c.tours from Client c where c.id = :clientId")
    List<Tour> findClientBookedTours(Long clientId);

}
