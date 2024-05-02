package com.calaton.travelagency.model.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tours")
@Getter
@Setter
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure_point")
    private String departurePoint;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "initial_price")
    private BigDecimal initialPrice;

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Guide guide;

    @ManyToMany(mappedBy = "tours")
    List<Client> clients;

    @ElementCollection
    @CollectionTable(name = "discounts", joinColumns = @JoinColumn(name = "tour_id"))
    @MapKeyColumn(name = "client_id")
    @Column(name = "discount")
    Map<Long, BigDecimal> discounts;
}
