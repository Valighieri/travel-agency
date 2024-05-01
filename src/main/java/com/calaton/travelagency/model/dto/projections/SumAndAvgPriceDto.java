package com.calaton.travelagency.model.dto.projections;

import java.math.BigDecimal;

public record SumAndAvgPriceDto(Long id, BigDecimal sum, Double avg) {
}
