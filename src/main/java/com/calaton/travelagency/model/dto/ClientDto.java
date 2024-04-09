package com.calaton.travelagency.model.dto;


import jakarta.validation.constraints.NotNull;

public record ClientDto(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String passportNumber){}
