package com.example.demo.service.impl;

import com.example.demo.entity.AlertRecord;
import com.example.demo.repository.AlertRecordRepository;
import com.example.demo.service.AlertService;

import java.util.List;

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
    public List<AlertRecord> getAlertsByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
}
