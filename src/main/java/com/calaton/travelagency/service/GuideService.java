package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.GuideMapper;
import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.dto.GuideDto;
import com.calaton.travelagency.repository.GuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public GuideDto getGuideById(Integer id) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow();
        return guideMapper.toGuideDto(guide);
    }

    public GuideDto setGuide(GuideDto guideDto) {
        Guide guide = guideMapper.toGuide(guideDto);
        Guide response = guideRepository.save(guide);
        return guideMapper.toGuideDto(response);
    }
}
