package com.example.springworkshop.watertracker.model;

import jakarta.persistence.*;

@Entity
public class WaterIntakeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time", nullable = false)
    private Long createdTime;

    @Column(name = "quantity", nullable = false)
    private Double qty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "water_log_id", nullable = false)
    private WaterLogModel waterLogModel;

    public WaterIntakeEntry(Long id, Long createdTime, Double qty, WaterLogModel waterLogModel) {
        this.id = id;
        this.createdTime = createdTime;
        this.qty = qty;
        this.waterLogModel = waterLogModel;
    }

    public WaterIntakeEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public WaterLogModel getWaterLogModel() {
        return waterLogModel;
    }

    public void setWaterLogModel(WaterLogModel waterLogModel) {
        this.waterLogModel = waterLogModel;
    }
}
