package com.example.demo.service.impl;

import com.example.demo.entity.BreachRecord;
import com.example.demo.repository.BreachRecordRepository;
import com.example.demo.service.BreachDetectionService;

import java.util.List;

public class BreachDetectionServiceImpl implements BreachDetectionService {

    private final BreachRecordRepository repository;

    public BreachDetectionServiceImpl(BreachRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRecord logBreach(BreachRecord breach) {
        return repository.save(breach);
    }

    @Override
    public BreachRecord resolveBreach(Long id) {
        BreachRecord breach = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach not found"));
        breach.setResolved(true);
        return repository.save(breach);
    }

    @Override
    public List<BreachRecord> getBreachesByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
}
