package com.example.springworkshop.dto;

import java.time.LocalDate;
import java.util.List;

public class WaterLogResponseDTO {
    private Long id;
    private LocalDate dateOfTracking;
    private String createdBy;
    private List<WaterIntakeResponseDTO> entries;


    public WaterLogResponseDTO() {
    }

    public WaterLogResponseDTO(Long id, LocalDate dateOfTracking, String createdBy, List<WaterIntakeResponseDTO> entries) {
        this.id = id;
        this.dateOfTracking = dateOfTracking;
        this.createdBy = createdBy;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfTracking() {
        return dateOfTracking;
    }

    public void setDateOfTracking(LocalDate dateOfTracking) {
        this.dateOfTracking = dateOfTracking;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<WaterIntakeResponseDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<WaterIntakeResponseDTO> entries) {
        this.entries = entries;
    }
}
