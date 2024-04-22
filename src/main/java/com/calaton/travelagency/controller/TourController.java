package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.dto.TourDto;
import com.calaton.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tours")
@AllArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TourDto getTourById(@PathVariable Long id){
        return tourService.getTourById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TourDto saveTour(@RequestBody TourDto tourDto){
        return tourService.saveTour(tourDto);
    }

}
