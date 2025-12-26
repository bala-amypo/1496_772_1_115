package com.example.demo.controller;

import com.example.demo.entity.TemperatureSensorLog;
import com.example.demo.service.TemperatureLogService;

import java.util.List;

public class TemperatureLogController {

    private final TemperatureLogService logService;

    public TemperatureLogController(TemperatureLogService logService) {
        this.logService = logService;
    }

    public TemperatureSensorLog record(TemperatureSensorLog log) {
        return logService.recordLog(log);
    }

    public List<TemperatureSensorLog> getByShipment(Long shipmentId) {
        return logService.getLogsByShipment(shipmentId);
    }
}
