package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.example.demo.entity.AlertRecord;
import com.example.demo.service.AlertService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts")
@SecurityRequirement(name = "bearerAuth")
public class AlertRecordController {

    private final AlertService alertService;

    public AlertRecordController(AlertService alertService) { 
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<AlertRecord> triggerAlert(@RequestBody AlertRecord alert) {

        AlertRecord savedAlert = alertService.triggerAlert(alert);

        if (savedAlert != null) {
            return ResponseEntity.status(201).body(savedAlert);
        } else {

            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}/acknowledge")
    public ResponseEntity<AlertRecord> acknowledgeAlert(@PathVariable Long id) {

        AlertRecord alert = alertService.findById(id);

        if (alert != null) {

            AlertRecord acknowledgedAlert = alertService.acknowledgeAlert(id);
            return ResponseEntity.status(200).body(acknowledgedAlert);
        } else {
     
            return ResponseEntity.status(404).build();
        }
    }


    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<AlertRecord>> getAlertsByShipment(@PathVariable Long shipmentId) {

        List<AlertRecord> alerts = alertService.getAlertsByShipment(shipmentId);

        if (alerts != null && !alerts.isEmpty()) {
            return ResponseEntity.status(200).body(alerts);
        } else {

            return ResponseEntity.status(404).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlertRecord> getAlertById(@PathVariable Long id) {
       
        AlertRecord alert = alertService.findById(id);

        if (alert != null) {
            return ResponseEntity.status(200).body(alert);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AlertRecord>> getAllAlerts() {
    
        List<AlertRecord> alerts = alertService.getAllAlerts();

        if (alerts != null && !alerts.isEmpty()) {
            return ResponseEntity.status(200).body(alerts);
        } else {

            return ResponseEntity.status(404).build();
        }
    }
}
