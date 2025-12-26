package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository shipmentRecordRepository;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository shipmentRecordRepository) {
            this.shipmentRecordRepository = shipmentRecordRepository;
        }

    @Override
    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return shipmentRecordRepository.save(shipment);
    }

    @Override
    public ShipmentRecord updateShipmentStatus(long id, String newStatus) {
        ShipmentRecord shipment = shipmentRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + id));
        shipment.setStatus(newStatus);
        return shipmentRecordRepository.save(shipment);
    }

    @Override
    public Optional<ShipmentRecord> getShipmentByCode(String shipmentCode) {
        return shipmentRecordRepository.findByShipmentCode(shipmentCode);
    }
    
    @Override
    public ShipmentRecord getShipmentById(long id) {
        return shipmentRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<ShipmentRecord> getAllShipments() {
        return shipmentRecordRepository.findAll();
    }
}
