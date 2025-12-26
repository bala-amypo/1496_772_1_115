package com.example.demo.service;

import com.example.demo.entity.TemperatureSensorLog;
import java.util.List;

public interface TemperatureLogService {

    TemperatureSensorLog recordLog(TemperatureSensorLog log);

    List<TemperatureSensorLog> getLogsByShipment(Long shipmentId);

    // ✅ REQUIRED by controller
    List<TemperatureSensorLog> getAllLogs();

    // ✅ REQUIRED by controller
    TemperatureSensorLog getLogById(Long id);
}
