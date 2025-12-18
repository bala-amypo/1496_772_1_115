package com.example.demo.service;

import com.example.demo.model.AlertRecord;
import com.example.demo.repository.AlertRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    
    private final AlertRecordRepository repository;
    
    public AlertServiceImpl(AlertRecordRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public AlertRecord triggerAlert(AlertRecord alert) {
        return repository.save(alert);
    }
    
    @Override
    public AlertRecord acknowledgeAlert(Long id) {
        AlertRecord alert = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setAcknowledged(true);
        return repository.save(alert);
    }
    
    @Override
    public List<AlertRecord> getAlertsByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
    
    @Override
    public List<AlertRecord> getAllAlerts() {
        return repository.findAll();
    }
}