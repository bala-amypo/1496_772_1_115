package com.example.demo.controller;

import com.example.demo.model.BreachRecord;
import com.example.demo.service.BreachDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breaches")
@Tag(name = "Breach Records", description = "Breach detection management")
public class BreachRecordController {

    private final BreachDetectionService service;

    public BreachRecordController(BreachDetectionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Log a new breach")
    public ResponseEntity<BreachRecord> logBreach(@RequestBody BreachRecord breach) {
        return ResponseEntity.ok(service.logBreach(breach));
    }

    @GetMapping
    @Operation(summary = "Get all breaches")
    public ResponseEntity<List<BreachRecord>> getAllBreaches() {
        return ResponseEntity.ok(service.getAllBreaches());
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get breaches by shipment ID")
    public ResponseEntity<List<BreachRecord>> getBreachesByShipment(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.getBreachesByShipment(shipmentId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get breach by ID")
    public ResponseEntity<BreachRecord> getBreachById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBreachById(id));
    }

    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve a breach")
    public ResponseEntity<BreachRecord> resolveBreach(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveBreach(id));
    }
}
