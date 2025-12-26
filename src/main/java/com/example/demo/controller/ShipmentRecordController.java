package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.ShipmentRecord; 
import com.example.demo.service.ShipmentRecordService;

@RestController
@RequestMapping("/api/shipments")
@SecurityRequirement(name = "bearerAuth")
public class ShipmentRecordController {

    private final ShipmentRecordService shipmentRecordService;

    public ShipmentRecordController(ShipmentRecordService shipmentRecordService) {
        this.shipmentRecordService = shipmentRecordService;
    }

    @PostMapping
    public ResponseEntity<ShipmentRecord> createShipment(@RequestBody ShipmentRecord shipment) {
        ShipmentRecord sh = shipmentRecordService.createShipment(shipment);

        if (sh != null) {
            return ResponseEntity.status(201).body(sh);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<ShipmentRecord> updateStatus(@PathVariable Long id,String status) {
        ShipmentRecord sh = shipmentRecordService.updateShipmentStatus(id, status);

        if (sh != null) {
            return ResponseEntity.status(201).body(sh);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/code/{shipmentCode}")
    public ResponseEntity<ShipmentRecord> getByCode(@PathVariable String shipmentCode) {
        Optional<ShipmentRecord> shipment = shipmentRecordService.getShipmentByCode(shipmentCode);

        if (shipment.isPresent()) {
            return ResponseEntity.status(200).body(shipment.get()); 
        } else {
            return ResponseEntity.status(404).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentRecord> getById(@PathVariable Long id) {
        ShipmentRecord sh = shipmentRecordService.getShipmentById(id);

        if (sh != null) {
            return ResponseEntity.status(201).body(sh);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ShipmentRecord>> getAll() {
        List<ShipmentRecord> list = shipmentRecordService.getAllShipments();
        return ResponseEntity.status(201).body(list);
        
    }
}
