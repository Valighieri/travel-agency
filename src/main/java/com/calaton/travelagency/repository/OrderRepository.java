package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //TODO:
    // - add date parameter (Most popular destination in a given year)
    // - remove nativeQuery parameter from queries
    // - add dto logic to queries (constructor and new [dto path] ...)
    @Query(value =
            "select sub.destination from " +
                    "(select count(*) as popularity, destination " +
                    "from orders " +
                    "join tours on orders.client_id = tours.id " +
                    "group by destination " +
                    "order by popularity desc " +
                    "limit 1) as sub",
            nativeQuery = true)
    String findMostPopularDestination();

    @Query(value =
            "select sub.client_id from " +
                    "(select client_id, sum(initial_price - initial_price / 100 * discount) as actual_price_sum " +
                    "from orders " +
                    "join tours on orders.tour_id = tours.id " +
                    "group by client_id " +
                    "order by actual_price_sum desc " +
                    "limit 1) as sub",
            nativeQuery = true)
    Integer findClientIdWithTheHighestRevenueOverall();


    @Query(value = "select tour_id, sum(initial_price - initial_price / 100 * discount) as actual_price_sum " +
            "from orders " +
            "join tours on orders.tour_id = tours.id " +
            "group by tour_id " +
            "order by tour_id", nativeQuery = true)
    String findToursAvgPrice();

}
