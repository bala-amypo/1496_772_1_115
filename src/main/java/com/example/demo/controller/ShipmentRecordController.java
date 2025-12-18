package com.example.demo.controller;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@Tag(name = "Shipments")
public class ShipmentRecordController {

    private final ShipmentRecordService service;

    public ShipmentRecordController(ShipmentRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ShipmentRecord create(@RequestBody ShipmentRecord s) {
        return service.createShipment(s);
    }

    @PutMapping("/{id}/status")
    public ShipmentRecord update(@PathVariable Long id, @RequestParam String status) {
        return service.updateShipmentStatus(id, status);
    }

    @GetMapping("/code/{code}")
    public ShipmentRecord byCode(@PathVariable String code) {
        return service.getShipmentByCode(code);
    }

    @GetMapping("/{id}")
    public ShipmentRecord byId(@PathVariable Long id) {
        return service.getShipmentById(id);
    }

    @GetMapping
    public List<ShipmentRecord> all() {
        return service.getAllShipments();
    }
}
