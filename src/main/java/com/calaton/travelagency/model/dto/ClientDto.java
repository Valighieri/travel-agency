package com.calaton.travelagency.model.dto;

import java.util.List;

public record ClientDto(String firstName, String lastName, String passportNumber, List<TourDto> tourDtoList) {
}
