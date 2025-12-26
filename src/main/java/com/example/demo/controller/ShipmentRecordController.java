package com.example.demo.controller;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;

import java.util.List;
import java.util.Optional;

public class ShipmentRecordController {

    private final ShipmentRecordService shipmentService;

    public ShipmentRecordController(ShipmentRecordService shipmentService) {
        this.shipmentService = shipmentService;
    }

    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return shipmentService.createShipment(shipment);
    }

    public ShipmentRecord updateStatus(Long id, String status) {
        return shipmentService.updateShipmentStatus(id, status);
    }

    public Optional<ShipmentRecord> getByCode(String code) {
        return shipmentService.getShipmentByCode(code);
    }

    public List<ShipmentRecord> getAll() {
        return shipmentService.getAllShipments();
    }
}
