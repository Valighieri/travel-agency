package com.calaton.travelagency.mapper;

import com.calaton.travelagency.model.domain.Guide;
import com.calaton.travelagency.model.dto.GuideDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuideMapper {

    GuideDto toGuideDto(Guide guide);

    Guide toGuide(GuideDto guideDto);
}
