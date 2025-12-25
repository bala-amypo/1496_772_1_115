package com.example.demo.service;

import com.example.demo.model.BreachRecord;
import com.example.demo.repository.BreachRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<BreachRecord> getBreachesByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }

    @Override
    public BreachRecord resolveBreach(Long id) {
        BreachRecord breach = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach not found"));
        breach.setResolved(true);
        return repository.save(breach);
    }

    @Override
    public BreachRecord getBreachById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach not found"));
    }

    @Override
    public List<BreachRecord> getAllBreaches() {
        return repository.findAll();
    }
}
