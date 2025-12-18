package com.example.demo.service;

import com.example.demo.model.AlertRecord;
import java.util.List;

public interface AlertService {
    AlertRecord triggerAlert(AlertRecord alert);
    AlertRecord acknowledgeAlert(Long id);
    List<AlertRecord> getAlertsByShipment(Long shipmentId);
    List<AlertRecord> getAllAlerts();
}
