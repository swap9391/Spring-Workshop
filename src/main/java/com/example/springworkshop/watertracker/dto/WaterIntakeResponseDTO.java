package com.example.springworkshop.watertracker.dto;

import java.time.LocalDate;
import java.util.List;

public class WaterIntakeResponseDTO {
    private Long createdTime;

    private Double qty;


    public WaterIntakeResponseDTO() {
    }

    public WaterIntakeResponseDTO(Long createdTime, Double qty) {
        this.createdTime = createdTime;
        this.qty = qty;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
