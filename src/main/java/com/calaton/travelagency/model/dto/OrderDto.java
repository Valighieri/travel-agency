package com.calaton.travelagency.model.dto;

import com.calaton.travelagency.model.domain.Client;
import com.calaton.travelagency.model.domain.Tour;

import java.math.BigDecimal;

public record OrderDto(Client client, Tour tour, BigDecimal discount) {
}
