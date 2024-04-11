package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.domain.Tour;
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

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tour getTourById(@PathVariable Integer id){
        return tourService.getTourById(id);
    }

    //TODO: Problem with saving embedded entity (Guide),
    // something with transactions ?
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Tour setTour(@RequestBody Tour tour){
        return tourService.setTour(tour);
    }
}
