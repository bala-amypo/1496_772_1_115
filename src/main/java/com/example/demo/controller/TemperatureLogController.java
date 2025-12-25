
package com.example.demo.controller;

import com.example.demo.model.TemperatureSensorLog;
import com.example.demo.service.TemperatureLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Temperature Logs", description = "Temperature log management")
public class TemperatureLogController {
    
    private final TemperatureLogService service;
    
    public TemperatureLogController(TemperatureLogService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Record a temperature log")
    public ResponseEntity<TemperatureSensorLog> recordLog(@RequestBody TemperatureSensorLog log) {
        return ResponseEntity.ok(service.recordLog(log));
    }
    
    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get logs for a shipment")
    public ResponseEntity<List<TemperatureSensorLog>> getLogsByShipment(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.getLogsByShipment(shipmentId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get log by ID")
    public ResponseEntity<TemperatureSensorLog> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLogById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all logs")
    public ResponseEntity<List<TemperatureSensorLog>> getAllLogs() {
        return ResponseEntity.ok(service.getAllLogs());
    }
}
