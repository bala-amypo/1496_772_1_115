package com.example.demo.service;

import com.example.demo.entity.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;
import org.springframework.stereotype.Service;

@Service
public class TemperatureSensorLogServiceImpl
        implements TemperatureSensorLogService {

    private final TemperatureSensorLogRepository repo;

    public TemperatureSensorLogServiceImpl(
            TemperatureSensorLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public TemperatureSensorLog saveLog(TemperatureSensorLog log) {
        return repo.save(log);
    }
}
