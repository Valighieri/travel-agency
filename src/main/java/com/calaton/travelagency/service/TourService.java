package com.calaton.travelagency.service;

import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.repository.GuideRepository;
import com.calaton.travelagency.repository.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final GuideRepository guideRepository;

    public Tour getTourById(Integer id) {
        return tourRepository.findById(id)
                .orElseThrow();
    }

    public Tour setTour(Tour tour) {
        Integer id = tour.getGuide().getId();
        if (id != null) {
            Guide guide = guideRepository.findById(id)
                    .orElseThrow();
            tour.setGuide(guide);
        }
        return tourRepository.save(tour);
    }


}
