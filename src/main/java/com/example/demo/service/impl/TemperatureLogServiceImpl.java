package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;
import com.example.demo.service.TemperatureLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureLogServiceImpl implements TemperatureLogService {

    private final TemperatureSensorLogRepository logRepo;

    public TemperatureLogServiceImpl(TemperatureSensorLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public TemperatureSensorLog recordLog(TemperatureSensorLog log) {
        return logRepo.save(log); // ✅ no validation
    }

    @Override
    public List<TemperatureSensorLog> getLogsByShipment(Long shipmentId) {
        return logRepo.findByShipmentId(shipmentId);
    }

    @Override
    public List<TemperatureSensorLog> getAllLogs() {
        return logRepo.findAll();
    }

    @Override
    public TemperatureSensorLog getLogById(Long id) {
        return logRepo.findById(id).orElse(null);
    }
}
