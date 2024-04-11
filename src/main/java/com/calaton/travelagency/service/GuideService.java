package com.calaton.travelagency.service;

import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.repository.GuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;

    public Guide getGuideById(Integer id) {
        return guideRepository.findById(id)
                .orElseThrow();
    }

    public Guide setGuide(Guide guide) {
        return guideRepository.save(guide);
    }
}
