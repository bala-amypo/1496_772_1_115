package com.example.demo.controller;

import com.example.demo.entity.BreachRecord;
import com.example.demo.service.BreachDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/breaches")
@SecurityRequirement(name = "bearerAuth")
public class BreachRecordController {

    private final BreachDetectionService breachService;

    public BreachRecordController(BreachDetectionService breachService) {
        this.breachService = breachService;
    }


    @PostMapping
    public ResponseEntity<BreachRecord> logBreach(@RequestBody BreachRecord breach) {
        BreachRecord savedBreach = breachService.logBreach(breach);
        return ResponseEntity.status(201).body(savedBreach);
    }


    @PutMapping("/{id}/resolve")
    public ResponseEntity<BreachRecord> resolveBreach(@PathVariable Long id) {
        BreachRecord resolvedBreach = breachService.resolveBreach(id);
        return ResponseEntity.status(200).body(resolvedBreach);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<BreachRecord>> getBreachesByShipment(@PathVariable Long shipmentId) {
        List<BreachRecord> breaches = breachService.getBreachesByShipment(shipmentId);
        return ResponseEntity.status(200).body(breaches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreachRecord> getBreachById(@PathVariable Long id) {
        BreachRecord breach = breachService.getBreachById(id);
        return ResponseEntity.status(200).body(breach);
    }

    @GetMapping
    public ResponseEntity<List<BreachRecord>> getAllBreaches() {
        List<BreachRecord> allBreaches = breachService.getAllBreaches();
        return ResponseEntity.status(200).body(allBreaches);
    }
}
