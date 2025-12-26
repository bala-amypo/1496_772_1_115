package com.example.demo.controller;

import com.example.demo.entity.BreachRecord;
import com.example.demo.service.BreachDetectionService;

import java.util.List;

public class BreachRecordController {

    private final BreachDetectionService breachService;

    public BreachRecordController(BreachDetectionService breachService) {
        this.breachService = breachService;
    }

    public BreachRecord log(BreachRecord breach) {
        return breachService.logBreach(breach);
    }

    public BreachRecord resolve(Long id) {
        return breachService.resolveBreach(id);
    }

    public List<BreachRecord> getByShipment(Long shipmentId) {
        return breachService.getBreachesByShipment(shipmentId);
    }
}
