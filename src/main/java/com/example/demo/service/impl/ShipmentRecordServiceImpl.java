package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;

import java.util.List;
import java.util.Optional;

public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository repository;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return repository.save(shipment);
    }

    @Override
    public ShipmentRecord updateShipmentStatus(Long id, String newStatus) {
        ShipmentRecord shipment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setStatus(newStatus);
        return repository.save(shipment);
    }

    @Override
    public Optional<ShipmentRecord> getShipmentByCode(String shipmentCode) {
        return repository.findByShipmentCode(shipmentCode);
    }

    @Override
    public List<ShipmentRecord> getAllShipments() {
        return repository.findAll();
    }
}
