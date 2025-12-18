package com.example.demo.controller;

import com.example.demo.model.AlertRecord;
import com.example.demo.service.AlertService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts", description = "Alert management operations")
public class AlertRecordController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Trigger a new alert")
    public ResponseEntity<AlertRecord> triggerAlert(
            @Valid @RequestBody AlertRecord alert) {
        return ResponseEntity.ok(service.triggerAlert(alert));
    }

    @PutMapping("/{id}/acknowledge")
    @Operation(summary = "Acknowledge an alert")
    public ResponseEntity<AlertRecord> acknowledgeAlert(@PathVariable Long id) {
        return ResponseEntity.ok(service.acknowledgeAlert(id));
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get alerts for a shipment")
    public ResponseEntity<List<AlertRecord>> getAlertsByShipment(
            @PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.getAlertsByShipment(shipmentId));
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<List<AlertRecord>> getAllAlerts() {
        return ResponseEntity.ok(service.getAllAlerts());
    }
}
