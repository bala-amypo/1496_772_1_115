package com.example.demo.controller;

import com.example.demo.entity.AlertRecord;
import com.example.demo.service.AlertService;

import java.util.List;

public class AlertRecordController {

    private final AlertService alertService;

    public AlertRecordController(AlertService alertService) {
        this.alertService = alertService;
    }

    public AlertRecord trigger(AlertRecord alert) {
        return alertService.triggerAlert(alert);
    }

    public List<AlertRecord> getByShipment(Long shipmentId) {
        return alertService.getAlertsByShipment(shipmentId);
    }
}
