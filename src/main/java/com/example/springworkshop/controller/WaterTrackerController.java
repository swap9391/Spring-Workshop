package com.example.springworkshop.controller;

import com.example.springworkshop.dto.WaterIntakeResponseDTO;
import com.example.springworkshop.dto.WaterLogResponseDTO;
import com.example.springworkshop.model.WaterLogModel;
import com.example.springworkshop.repository.WaterLogJPADataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/water-tracker")
public class WaterTrackerController {

    WaterLogJPADataRepository waterLogJPADataRepository;

    public WaterTrackerController(WaterLogJPADataRepository waterLogJPADataRepository) {
        super();
        this.waterLogJPADataRepository = waterLogJPADataRepository;
    }



    /*Adds a new water log entry
    *
    * createdBy mapped with authenticated user
    * dateOfTracking mapped oneToMany relation between waterTrackingEntries
    * WaterIntakeEntry has annotation  @OneToMany(mappedBy = "waterLogModel", cascade = CascadeType.ALL, orphanRemoval = true)
    * Which will delete all child automatically if parent has deleted
    * if record already present for the date waterTrackingEntries keep adding the data to the same date
    * * {
        "createdBy": "swapnil.jadhav@gmail.com",
        "dateOfTracking": "2025-08-16",
        "waterTrackingEntries": [
        {
            "qty": 240
         },
        {
            "qty": 300
        }
       ]
    }
    *
    * */

    @PostMapping(value = "/add-water-log")
    ResponseEntity<Object> addWaterLog(@RequestBody WaterLogModel waterLogModel, Authentication authentication) {

        // Get username/email of the logged-in user
        String currentUserEmail = authentication.getName();


        LocalDate requestDate = waterLogModel.getDateOfTracking();
        long now = System.currentTimeMillis();

        WaterLogModel existingLog = waterLogJPADataRepository.findByDateOfTracking(requestDate).orElse(null);

        WaterLogModel saved ;

        if (existingLog != null) {
            saved = updateWaterTrackingEntriesToSameDate(waterLogModel, now, existingLog);
        } else {
            saved = createWaterEntryWithUniqueDate(waterLogModel, now,currentUserEmail);
        }

        // Map to Response DTO
        WaterLogResponseDTO response = new WaterLogResponseDTO();
        response.setId(saved.getId());
        response.setDateOfTracking(saved.getDateOfTracking());
        response.setCreatedBy(saved.getCreatedBy());
        response.setEntries(saved.getWaterTrackingEntries().stream()
                .map(e -> {
                    WaterIntakeResponseDTO dto = new WaterIntakeResponseDTO();
                    dto.setQty(e.getQty());
                    dto.setCreatedTime(e.getCreatedTime());
                    return dto;
                })
                .toList()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private WaterLogModel createWaterEntryWithUniqueDate(WaterLogModel waterLogModel, long now, String currentUserEmail) {
        WaterLogModel saved;
        waterLogModel.setCreatedTime(now);
        waterLogModel.setUpdatedTime(now);
        waterLogModel.setCreatedBy(currentUserEmail);

        if (waterLogModel.getWaterTrackingEntries() == null) {
            waterLogModel.setWaterTrackingEntries(new ArrayList<>());
        } else {
            waterLogModel.getWaterTrackingEntries().forEach(entry -> entry.setCreatedTime(now));
        }

        waterLogModel.getWaterTrackingEntries().forEach(entry -> entry.setWaterLogModel(waterLogModel));
        saved = waterLogJPADataRepository.save(waterLogModel);
        return saved;
    }

    private WaterLogModel updateWaterTrackingEntriesToSameDate(WaterLogModel waterLogModel, long now, WaterLogModel existingLog) {
        WaterLogModel saved;
        if (waterLogModel.getWaterTrackingEntries() != null) {
            waterLogModel.getWaterTrackingEntries().forEach(entry -> {
                entry.setWaterLogModel(existingLog);
                entry.setCreatedTime(now);
                existingLog.getWaterTrackingEntries().add(entry);
            });
        }
        existingLog.setUpdatedTime(now);

        saved = waterLogJPADataRepository.save(existingLog);
        return saved;
    }

    @GetMapping(value = "/fetch-all-water-log")
    ResponseEntity<Object> fetchAllWaterLog() {

        List<WaterLogResponseDTO> logs = waterLogJPADataRepository.findAll()
                .stream()
                .map(log -> new WaterLogResponseDTO(
                        log.getId(),
                        log.getDateOfTracking(),
                        log.getCreatedBy(),
                        log.getWaterTrackingEntries()
                                .stream()
                                .map(e -> new WaterIntakeResponseDTO(e.getCreatedTime(), e.getQty()))
                                .toList()
                ))
                .toList();

        if (logs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No water logs found");
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping(value = "/fetch-water-log/{id}")
    ResponseEntity<Object> fetchAllWaterLogById(@PathVariable long id) {
        WaterLogResponseDTO logs = waterLogJPADataRepository.findById(id)
                .map(log -> new WaterLogResponseDTO(
                        log.getId(),
                        log.getDateOfTracking(),
                        log.getCreatedBy(),
                        log.getWaterTrackingEntries()
                                .stream()
                                .map(e -> new WaterIntakeResponseDTO(e.getCreatedTime(), e.getQty()))
                                .toList()
                )) .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Water log not found with id " + id));
        return ResponseEntity.ok(logs);
    }

    @DeleteMapping("/remove-water-log/{id}")
    public ResponseEntity<Object> deleteWaterLogById(@PathVariable long id){

        if (!waterLogJPADataRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Water Log By ID " + id + " not found"
            );
        }

        waterLogJPADataRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
