package com.calaton.travelagency.model.dto;

import com.calaton.travelagency.model.domain.Guide;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TourDto(String departurePoint, String destination, LocalDate departureDate,
                      LocalDate returnDate, BigDecimal initialPrice, Guide guide) {
}
