package com.example.demo.repository;

import com.example.demo.entity.TemperatureSensorLog;
import java.util.List;

public interface TemperatureSensorLogRepository {

    TemperatureSensorLog save(TemperatureSensorLog log);

    List<TemperatureSensorLog> findByShipmentId(Long shipmentId);
}
