package com.calaton.travelagency.mapper;

import com.calaton.travelagency.model.domain.Tour;
import com.calaton.travelagency.model.dto.TourDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourMapper {

    TourDto toDto(Tour tour);

    Tour toEntity(TourDto tourDto);

}
