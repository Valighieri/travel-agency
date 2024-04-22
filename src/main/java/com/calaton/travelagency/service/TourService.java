package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.TourMapper;
import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.TourDto;
import com.calaton.travelagency.model.exception.InvalidDataException;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
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


    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tour not found with id = " + id));

        return tourMapper.toDto(tour);
    }

    // TODO: - change dto structure;
    //  (we don't need attached entity in dto, we don't use cascade operations)
    //  (for now: using attached entity from dto for getting this entity id)

    public TourDto saveTour(TourDto tourDto) {
        Tour tour = tourMapper.toEntity(tourDto);
        Long guideId = tour.getGuide().getId();
        if (guideId == null) {
            throw new InvalidDataException("Guide id must not be null");
        }
        Guide guide = guideRepository.findById(guideId).orElseThrow();
        tour.setGuide(guide);

        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

}
