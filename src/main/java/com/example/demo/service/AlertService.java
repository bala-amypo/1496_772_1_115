package com.example.demo.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AlertRecord;

@Service
public interface AlertService{
    AlertRecord triggerAlert(AlertRecord alert);
    AlertRecord acknowledgeAlert(Long id);
    List<AlertRecord> getAlertsByShipment(Long shipmentId);
    List<AlertRecord> getAllAlerts();
    AlertRecord findById(Long id);
}