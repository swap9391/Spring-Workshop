package com.example.springworkshop.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "water_log")
public class WaterLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_time", nullable = false)
    private Long createdTime;

    @Column(name = "updated_time")
    private Long updatedTime;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "date_of_tracking", nullable = false)
    private LocalDate dateOfTracking;


/*
    1. @OneToMany
    Declares a one-to-many relationship.
    Means: One WaterLogClass can have many WaterIntakeEntry objects.

    2. mappedBy = "waterLog"
    Says:
    “The other side of this relationship (in WaterIntakeEntry) owns the foreign key.”

    3. cascade = CascadeType.ALL
    Means all operations done on the parent will also be done on the children.
    Includes:
    PERSIST → saving the parent also saves its children
    MERGE → updating parent also updates children
    REMOVE → deleting parent deletes children
    REFRESH & DETACH also included

    4. orphanRemoval = true
    Means: if a child entity is removed from the parent’s list, JPA will delete it from the database automatically.
*/

    @OneToMany(mappedBy = "waterLogModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterIntakeEntry> waterTrackingEntries = new ArrayList<>();


    public WaterLogModel() {
    }

    public WaterLogModel(long id, Long createdTime, Long updatedTime, String createdBy, LocalDate dateOfTracking, List<WaterIntakeEntry> waterTrackingEntries) {
        this.id = id;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.createdBy = createdBy;
        this.dateOfTracking = dateOfTracking;
        this.waterTrackingEntries = waterTrackingEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getDateOfTracking() {
        return dateOfTracking;
    }

    public void setDateOfTracking(LocalDate dateOfTracking) {
        this.dateOfTracking = dateOfTracking;
    }

    public List<WaterIntakeEntry> getWaterTrackingEntries() {
        return waterTrackingEntries;
    }

    public void setWaterTrackingEntries(List<WaterIntakeEntry> waterTrackingEntries) {
        this.waterTrackingEntries = waterTrackingEntries;
    }
}
