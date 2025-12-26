package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository repository;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository repository) {
        this.repository = repository;
    }

    public ShipmentRecord createShipment(ShipmentRecord s) { return repository.save(s); }
    public ShipmentRecord updateShipmentStatus(Long id, String status) {
        ShipmentRecord s = repository.findById(id).orElseThrow();
        s.setStatus(status);
        return repository.save(s);
    }
    public ShipmentRecord getShipmentByCode(String code) { return repository.findByShipmentCode(code); }
    public ShipmentRecord getShipmentById(Long id) { return repository.findById(id).orElse(null); }
    public List<ShipmentRecord> getAllShipments() { return repository.findAll(); }
}
