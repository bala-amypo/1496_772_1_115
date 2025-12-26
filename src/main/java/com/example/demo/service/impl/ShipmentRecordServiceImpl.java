package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;

import java.util.List;
import java.util.Optional;

public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository shipmentRepo;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository shipmentRepo) {
        this.shipmentRepo = shipmentRepo;
    }

    @Override
    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return shipmentRepo.save(shipment);
    }

    @Override
    public ShipmentRecord updateShipmentStatus(Long id, String newStatus) {
        ShipmentRecord shipment = shipmentRepo.findById(id).orElseThrow();
        shipment.setStatus(newStatus);
        return shipmentRepo.save(shipment);
    }

    @Override
    public Optional<ShipmentRecord> getShipmentByCode(String shipmentCode) {
        return shipmentRepo.findByShipmentCode(shipmentCode);
    }

    @Override
    public List<ShipmentRecord> getAllShipments() {
        return shipmentRepo.findAll();
    }
}
