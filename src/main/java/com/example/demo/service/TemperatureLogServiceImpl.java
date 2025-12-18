package com.example.demo.service;

import com.example.demo.model.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemperatureLogServiceImpl implements TemperatureLogService {
    
    private final TemperatureSensorLogRepository repository;
    
    public TemperatureLogServiceImpl(TemperatureSensorLogRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public TemperatureSensorLog recordLog(TemperatureSensorLog log) {
        return repository.save(log);
    }
    
    @Override
    public List<TemperatureSensorLog> getLogsByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
    
    @Override
    public TemperatureSensorLog getLogById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Log not found"));
    }
    
    @Override
    public List<TemperatureSensorLog> getAllLogs() {
        return repository.findAll();
    }
}