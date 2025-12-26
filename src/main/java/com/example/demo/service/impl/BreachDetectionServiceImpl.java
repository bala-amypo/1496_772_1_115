package com.example.demo.service.impl;

import com.example.demo.entity.BreachRecord;
import com.example.demo.repository.BreachRecordRepository;
import com.example.demo.service.BreachDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachDetectionServiceImpl implements BreachDetectionService {

    private final BreachRecordRepository breachRepo;

    public BreachDetectionServiceImpl(BreachRecordRepository breachRepo) {
        this.breachRepo = breachRepo;
    }

    @Override
    public BreachRecord logBreach(BreachRecord breach) {
        return breachRepo.save(breach); // ✅ no validation
    }

    @Override
    public BreachRecord resolveBreach(Long id) {
        BreachRecord br = breachRepo.findById(id).orElse(null);
        if (br != null) {
            br.setResolved(true);
            breachRepo.save(br);
        }
        return br;
    }

    @Override
    public List<BreachRecord> getBreachesByShipment(Long shipmentId) {
        return breachRepo.findByShipmentId(shipmentId);
    }

    @Override
    public BreachRecord getBreachById(Long id) {
        return breachRepo.findById(id).orElse(null);
    }

    @Override
    public List<BreachRecord> getAllBreaches() {
        return breachRepo.findAll();
    }
}
