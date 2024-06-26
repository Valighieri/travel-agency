package com.calaton.travelagency.controller;

import com.calaton.travelagency.model.dto.GuideDto;
import com.calaton.travelagency.service.GuideService;
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
@RequestMapping("api/v1/guides")
@AllArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GuideDto getGuideById(@PathVariable Long id) {
        return guideService.getGuideById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public GuideDto saveGuide(@RequestBody GuideDto guideDto){
        return guideService.saveGuide(guideDto);
    }

    @GetMapping("/revenue/{year}")
    @ResponseStatus(HttpStatus.OK)
    public GuideDto getGuideWithTheHighestRevenue(@PathVariable Integer year) {
        return guideService.getGuideWithTheHighestRevenue(year);
    }

}
