package com.calaton.travelagency.util;

import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.exception.DateConflictException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TourAnalyzer {

    // Guides can't guide 2 tours at the same time
    // Clients can't book 2 tours at the same time
    public void checkIfTheTourPeriodIsAvailable(List<Tour> bookedTours, Tour tour) {
        for (Tour bookedTour : bookedTours) {
            boolean tourCompletelyBeforeBookedTour = tour.getReturnDate().isBefore(bookedTour.getDepartureDate());
            boolean tourCompletelyAfterBookedTour = tour.getDepartureDate().isAfter(bookedTour.getReturnDate());
            if (!tourCompletelyBeforeBookedTour && !tourCompletelyAfterBookedTour) {
                throw new DateConflictException("Conflicts by date with another tour");
            }
        }
    }

}
