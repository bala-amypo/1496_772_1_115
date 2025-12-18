package com.example.demo.controller;

import com.example.demo.model.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shipments")
@Tag(name = "Shipments", description = "Shipment management operations")
public class ShipmentRecordController {
    
    private final ShipmentRecordService service;
    
    public ShipmentRecordController(ShipmentRecordService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Create a new shipment")
    public ResponseEntity<ShipmentRecord> createShipment(@RequestBody ShipmentRecord shipment) {
        return ResponseEntity.ok(service.createShipment(shipment));
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update shipment status")
    public ResponseEntity<ShipmentRecord> updateStatus(@PathVariable Long id, 
                                                       @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(service.updateShipmentStatus(id, status));
    }
    
    @GetMapping("/code/{shipmentCode}")
    @Operation(summary = "Get shipment by code")
    public ResponseEntity<ShipmentRecord> getByCode(@PathVariable String shipmentCode) {
        return ResponseEntity.ok(service.getShipmentByCode(shipmentCode));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get shipment by ID")
    public ResponseEntity<ShipmentRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getShipmentById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all shipments")
    public ResponseEntity<List<ShipmentRecord>> getAll() {
        return ResponseEntity.ok(service.getAllShipments());
    }
}