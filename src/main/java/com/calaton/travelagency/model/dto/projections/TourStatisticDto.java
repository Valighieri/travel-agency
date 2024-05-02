package com.calaton.travelagency.model.dto.projections;

import com.calaton.travelagency.model.dto.TourDto;

import java.math.BigDecimal;

public record TourStatisticDto(TourDto tourDto, BigDecimal sum, Double avg) {
}
