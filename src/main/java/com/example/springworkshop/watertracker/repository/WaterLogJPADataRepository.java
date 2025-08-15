package com.example.springworkshop.watertracker.repository;

import com.example.springworkshop.watertracker.model.WaterLogModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WaterLogJPADataRepository extends JpaRepository<WaterLogModel,Long> {

    Optional<WaterLogModel> findByDateOfTracking(LocalDate dateOfTracking);
}
