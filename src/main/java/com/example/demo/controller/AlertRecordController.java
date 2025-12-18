package com.example.demo.controller;

import com.example.demo.model.AlertRecord;
import com.example.demo.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts", description = "Alert management operations")
public class AlertRecordController {

    private final AlertService alertService;

    public AlertRecordController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    @Operation(summary = "Trigger a new alert")
    public ResponseEntity<AlertRecord> triggerAlert(
            @RequestBody AlertRecord alertRecord) {

        return ResponseEntity.ok(alertService.triggerAlert(alertRecord));
    }

    @PutMapping("/{id}/acknowledge")
    @Operation(summary = "Acknowledge an alert")
    public ResponseEntity<AlertRecord> acknowledgeAlert(
            @PathVariable Long id) {

        return ResponseEntity.ok(alertService.acknowledgeAlert(id));
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get alerts by shipment ID")
    public ResponseEntity<List<AlertRecord>> getAlertsByShipment(
            @PathVariable Long shipmentId) {

        return ResponseEntity.ok(alertService.getAlertsByShipment(shipmentId));
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<List<AlertRecord>> getAllAlerts() {

        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
