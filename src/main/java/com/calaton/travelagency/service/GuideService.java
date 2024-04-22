package com.calaton.travelagency.service;

import com.calaton.travelagency.mapper.GuideMapper;
import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.dto.GuideDto;
import com.calaton.travelagency.model.exception.ResourceNotFoundException;
import com.calaton.travelagency.repository.GuideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;

    private final GuideMapper guideMapper;

    public GuideDto getGuideById(Long id) {
        Guide guide = guideRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Guide not found with id = " + id));
        return guideMapper.toDto(guide);
    }

    public GuideDto saveGuide(GuideDto guideDto) {
        Guide guide = guideMapper.toEntity(guideDto);
        guide = guideRepository.save(guide);
        return guideMapper.toDto(guide);
    }
}
