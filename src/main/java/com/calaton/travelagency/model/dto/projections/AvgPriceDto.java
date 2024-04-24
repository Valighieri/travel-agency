package com.calaton.travelagency.model.dto.projections;

public class AvgPriceDto {

    private Long tour_id;
    private Double avgPrice;

    public AvgPriceDto(Long tour_id, Double avgPrice) {
        this.tour_id = tour_id;
        this.avgPrice = avgPrice;
    }

    public Long getTour_id() {
        return tour_id;
    }

    public void setTour_id(Long tour_id) {
        this.tour_id = tour_id;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
