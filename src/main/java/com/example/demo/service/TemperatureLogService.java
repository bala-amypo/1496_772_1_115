package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;
import com.example.demo.service.TemperatureLogService;

import java.util.List;

public class TemperatureLogServiceImpl implements TemperatureLogService {

    private final TemperatureSensorLogRepository logRepo;

    public TemperatureLogServiceImpl(TemperatureSensorLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public TemperatureSensorLog recordLog(TemperatureSensorLog log) {
        return logRepo.save(log);
    }

    @Override
    public List<TemperatureSensorLog> getLogsByShipment(Long shipmentId) {
        return logRepo.findByShipmentId(shipmentId);
    }
}
