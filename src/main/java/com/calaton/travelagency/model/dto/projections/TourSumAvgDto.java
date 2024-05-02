package com.calaton.travelagency.model.dto.projections;

import com.calaton.travelagency.model.domain.Tour;

import java.math.BigDecimal;

public record TourSumAvgDto(Tour tour, BigDecimal sum, Double avg) {
}
