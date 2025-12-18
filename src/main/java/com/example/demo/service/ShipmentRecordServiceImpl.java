package com.example.demo.service;

import com.example.demo.model.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {
    
    private final ShipmentRecordRepository repository;
    
    public ShipmentRecordServiceImpl(ShipmentRecordRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return repository.save(shipment);
    }
    
    @Override
    public ShipmentRecord updateShipmentStatus(Long id, String status) {
        ShipmentRecord shipment = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setStatus(status);
        return repository.save(shipment);
    }
    
    @Override
    public ShipmentRecord getShipmentByCode(String code) {
        return repository.findByShipmentCode(code);
    }
    
    @Override
    public ShipmentRecord getShipmentById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }
    
    @Override
    public List<ShipmentRecord> getAllShipments() {
        return repository.findAll();
    }
}

// ==================== TemperatureLogService.java ====================
package com.example.demo.service;

import com.example.demo.model.TemperatureSensorLog;
import java.util.List;

public interface TemperatureLogService {
    TemperatureSensorLog recordLog(TemperatureSensorLog log);
    List<TemperatureSensorLog> getLogsByShipment(Long shipmentId);
    TemperatureSensorLog getLogById(Long id);
    List<TemperatureSensorLog> getAllLogs();
}
