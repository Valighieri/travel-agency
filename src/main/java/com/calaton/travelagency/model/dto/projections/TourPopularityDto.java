package com.calaton.travelagency.model.dto.projections;

public class TourPopularityDto {

    private Long id;

    private Long count;

    public TourPopularityDto(Long id, Long count) {
        this.id = id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
