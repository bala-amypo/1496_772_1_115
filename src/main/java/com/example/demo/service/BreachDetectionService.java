package com.example.demo.service;

import com.example.demo.model.BreachRecord;
import java.util.List;

public interface BreachDetectionService {
    BreachRecord logBreach(BreachRecord breach);
    List<BreachRecord> getBreachesByShipment(Long shipmentId);
    BreachRecord resolveBreach(Long id);
    BreachRecord getBreachById(Long id);
    List<BreachRecord> getAllBreaches();
}