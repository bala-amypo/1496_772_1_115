package com.example.demo.service;

import com.example.demo.model.TemperatureSensorLog;
import java.util.List;

public interface TemperatureLogService {
    List<TemperatureSensorLog> getAllLogs();
    TemperatureSensorLog getLogById(Long id);
    TemperatureSensorLog saveLog(TemperatureSensorLog log);
}
