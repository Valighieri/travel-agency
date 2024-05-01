package com.calaton.travelagency.model.dto;

import java.math.BigDecimal;

public record BookingTourDto(Long clientId, Long tourId, BigDecimal discount) {
}
