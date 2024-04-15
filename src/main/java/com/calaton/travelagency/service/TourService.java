package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.TourMapper;
import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.TourDto;
import com.calaton.travelagency.repository.GuideRepository;
import com.calaton.travelagency.repository.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final GuideRepository guideRepository;
    private final TourMapper tourMapper;


    public TourDto getTourById(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow();
        return tourMapper.toTourDto(tour);
    }

    public TourDto setTour(TourDto tourDto) {
        Tour tour = tourMapper.toTour(tourDto);
        Integer id = tour.getGuide().getId();
        if (id != null) {
            Guide guide = guideRepository.findById(id)
                    .orElseThrow();
            tour.setGuide(guide);
        }
        Tour response = tourRepository.save(tour);
        return tourMapper.toTourDto(response);
    }


}
