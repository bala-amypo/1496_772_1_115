package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository repo;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository repo) {
        this.repo = repo;
    }

    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return repo.save(shipment);
    }

    public ShipmentRecord updateShipmentStatus(Long id, String status) {
        ShipmentRecord s = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
        s.setStatus(status);
        return repo.save(s);
    }

    public Optional<ShipmentRecord> getShipmentByCode(String code) {
        return repo.findByShipmentCode(code);
    }

    public List<ShipmentRecord> getAllShipments() {
        return repo.findAll();
    }
}
