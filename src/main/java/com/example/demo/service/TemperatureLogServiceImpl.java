package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.model.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;

@Service
public class TemperatureLogServiceImpl implements TemperatureLogService {

    private final TemperatureSensorLogRepository repository;

    public TemperatureLogServiceImpl(TemperatureSensorLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TemperatureSensorLog> getAllLogs() {
        return repository.findAll();
    }

    @Override
    public TemperatureSensorLog getLogById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TemperatureSensorLog saveLog(TemperatureSensorLog log) {
        return repository.save(log);
    }
}
