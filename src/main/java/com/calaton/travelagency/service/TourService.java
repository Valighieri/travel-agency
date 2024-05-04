package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.TourMapper;
import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.TourDto;
import com.calaton.travelagency.model.dto.projections.CountryCountDto;
import com.calaton.travelagency.model.dto.projections.TourSumAvgDto;
import com.calaton.travelagency.model.dto.projections.TourStatisticDto;
import com.calaton.travelagency.model.exception.DateConflictException;
import com.calaton.travelagency.model.exception.InvalidDataException;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
import com.calaton.travelagency.repository.GuideRepository;
import com.calaton.travelagency.repository.TourRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TourService {

    private final TourRepository tourRepository;
    private final GuideRepository guideRepository;

    private final TourMapper tourMapper;

    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tour not found with id = " + id));

        return tourMapper.toDto(tour);
    }

    public TourDto saveTour(TourDto tourDto) {
        Tour tour = tourMapper.toEntity(tourDto);
        Long guideId = tour.getGuide().getId();
        if (guideId == null) {
            throw new InvalidDataException("Guide id must not be null");
        }
        Guide guide = guideRepository.findById(guideId).orElseThrow(
                () -> new ResourceNotFoundException("Guide not found with id = " + guideId));
        checkIfTheTourPeriodIsAvailable(guideId, tour);
        tour.setGuide(guide);
        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    // Guides can't guide 2 tours at the same time
    private void checkIfTheTourPeriodIsAvailable(Long guideId, Tour tour) {
        List<Tour> guideActualTours = tourRepository.findGuideActualTours(guideId);
        for (Tour actualTour : guideActualTours) {
            boolean tourCompletelyBefore = tour.getReturnDate().isBefore(actualTour.getDepartureDate());
            boolean tourCompletelyAfter = tour.getDepartureDate().isAfter(actualTour.getReturnDate());
            if (!tourCompletelyBefore && !tourCompletelyAfter) {
                throw new DateConflictException("Guide has conflict by date with another tour");
            }
        }
    }

    public CountryCountDto getTheMostPopularDestination(Integer year) {
        return tourRepository.findDestinationsOrderedByPopularity(year).orElseThrow(
                () -> new ResourceNotFoundException("Tour not found"));
    }

    public TourDto getTourFromTop3WithTheLowestSellingPrice() {
        List<Tour> tours = tourRepository.findToursOrderedByPopularity(Limit.of(3));
        Tour tour = tours.stream().min(Comparator.comparing(Tour::getInitialPrice))
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        return tourMapper.toDto(tour);
    }

    public TourStatisticDto getTourAvgPriceAndTotalAmount(Long tourId) {
        TourSumAvgDto tourSumAvg = tourRepository.findTourAvgPriceAndTotalAmount(tourId)
                .orElseThrow(() -> new ResourceNotFoundException("Tour not found with id = " + tourId));
        TourDto tourDto = tourMapper.toDto(tourSumAvg.tour());
        return new TourStatisticDto(tourDto, tourSumAvg.sum(), tourSumAvg.avg());
    }

}
