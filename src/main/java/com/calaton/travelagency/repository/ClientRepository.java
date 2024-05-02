package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Tour;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Task: "Customer which did not order a tour in a given year"
    @Query(value = "select client from Client client where client.id not in " +
            "(select c.id from Client c, in(c.tours) t where year(t.departureDate) = :year group by c.id)")
    List<Client> findClientsWithoutOrders(Integer year);

    // Task: "Which customer got the highest discount (difference between initial and
    //      transaction price)"
    @Query(value = "select c from Client c, in(c.discounts) d where d = " +
            "(select max(d) from Client c, in(c.discounts) d)")
    List<Client> findClientsWithTheHighestDiscount();

    // Task: "Customer generated the highest revenue overall for the travel agency."
    @Query(value = "select c from Client c, in(c.tours) t " +
            "group by c order by sum(t.initialPrice) desc")
    List<Client> findClientsWithTheHighestRevenueOverall(Limit limit);

    @Query(value = "select c.tours from Client c where c.id = :clientId")
    List<Tour> findClientBookedTours(Long clientId);

}
