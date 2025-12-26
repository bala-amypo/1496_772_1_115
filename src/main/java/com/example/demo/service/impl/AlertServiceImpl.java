package com.example.demo.service.impl;

import com.example.demo.entity.AlertRecord;
import com.example.demo.repository.AlertRecordRepository;
import com.example.demo.service.AlertService;

import java.util.List;

public class AlertServiceImpl implements AlertService {

    private final AlertRecordRepository alertRepo;

    public AlertServiceImpl(AlertRecordRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public AlertRecord triggerAlert(AlertRecord alert) {
        return alertRepo.save(alert);
    }

    @Override
    public List<AlertRecord> getAlertsByShipment(Long shipmentId) {
        return alertRepo.findByShipmentId(shipmentId);
    }
}
