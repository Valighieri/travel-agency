package com.calaton.travelagency.repository;

import com.calaton.travelagency.model.domain.Order;
import com.calaton.travelagency.model.dto.projections.TourPopularityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // # "Most popular destination in a given year"
    // without "limit 1" and "order by" (return type can't be Optional<...>)
    // (we can't return only one raw, because of jpql doesn't have limit statement)
    @Query(value = "select count(o.id), o.tour.destination from Order o " +
            "where year(o.tour.departureDate) = :year group by o.tour.destination")
    List<Object[]> findMostPopularDestination(Integer year);

    // # "Customer which did not order a tour in a given year"
    @Query(value = "select o.client.id from Order o where year(o.tour.departureDate) != :year " +
            "group by o.client.id")
    List<Object[]> findClientsWithoutOrders(Integer year);

    // # "The average price and total amount for which a tour was sold"
    // Is tour id enough ? Do we want to map tour id on TourDto ?
    @Query(value = "select o.tour.id, sum(o.tour.initialPrice - o.tour.initialPrice / 100 * o.discount)," +
            "avg(o.tour.initialPrice - o.tour.initialPrice / 100 * o.discount) from Order o group by o.tour.id")
    List<Object[]> findToursAvgPriceAndTotalAmount();

    // # "The most popular trip (top 3) with the lowest selling price"
    // without "limit 1" and "order by" (return type can't be Optional<...>)
    @Query(value = "select new com.calaton.travelagency.model.dto.projections.TourPopularityDto(" +
            "o.tour.id, count(o.tour.id)) from Order o group by o.tour.id")
    List<TourPopularityDto> findToursPopularity();

    // # "Which tour guide generated the highest revenue, i.e., handled tours for the
    //      highest total in a given year."
    // without "limit 1" and "order by" (return type can't be Optional<...>)
    @Query(value = "select o.tour.guide.id, sum(o.tour.initialPrice - o.tour.initialPrice / 100 * o.discount)" +
            " from Order o where year(o.tour.departureDate) = :year group by o.tour.guide.id")
    List<Object[]> findGuidesSumRevenue(Integer year);

    // # "Which customer got the highest discount (difference between initial and
    //      transaction price)"
    @Query(value = "select o.client.id, max(o.tour.initialPrice / 100 * o.discount) from Order o " +
            "group by o.client.id")
    List<Object[]> findCustomersHighestDiscounts();

    // # "Customer generated the highest revenue overall for the travel agency."
    // without "limit 1" and "order by" (return type can't be Optional<...>)
    @Query(value = "select o.client.id, sum(o.tour.initialPrice - o.tour.initialPrice / 100 * o.discount)" +
            " from Order o group by o.client.id")
    List<Object[]> findClientWithTheHighestRevenueOverall();



//    // # "The average price and total amount for which a tour was sold"
//    @Query(value = "select new com.calaton.travelagency.model.dto.projections.AvgPriceDto" +
//            "(o.tour.id, avg(o.tour.initialPrice - o.tour.initialPrice / 100 * o.discount))" +
//            " from Order o group by o.tour.id")
//    List<AvgPriceDto> findToursAvgPrice();
}
