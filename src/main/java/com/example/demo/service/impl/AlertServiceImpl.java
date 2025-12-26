package com.example.demo.service.impl;

import com.example.demo.entity.AlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AlertRecordRepository;
import com.example.demo.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRecordRepository alertRecordRepository;

    public AlertServiceImpl(AlertRecordRepository alertRecordRepository) {
        this.alertRecordRepository = alertRecordRepository;
    }

    @Override
    public AlertRecord triggerAlert(AlertRecord alert) {
        return alertRecordRepository.save(alert);
    }

    @Override
    public AlertRecord acknowledgeAlert(Long id) {
        Optional<AlertRecord> alertOptional = alertRecordRepository.findById(id);
        if (alertOptional.isPresent()) {
            AlertRecord alert = alertOptional.get();
            alert.setAcknowledged(true);
            return alertRecordRepository.save(alert); 
        } else {
            throw new ResourceNotFoundException("Alert with id " + id + " not found.");
        }
    }

    @Override
    public List<AlertRecord> getAlertsByShipment(Long shipmentId) {
        return alertRecordRepository.findByShipmentId(shipmentId);
    }

    @Override
    public List<AlertRecord> getAllAlerts() {
        return alertRecordRepository.findAll();
    }
    @Override
    public AlertRecord findById(Long id) {
        return alertRecordRepository.findById(id).orElse(null);
    }
}
