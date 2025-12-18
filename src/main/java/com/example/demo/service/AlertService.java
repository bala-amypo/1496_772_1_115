

// ==================== ShipmentRecordServiceImpl.java ====================
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

// ==================== TemperatureLogServiceImpl.java ====================
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

// ==================== TemperatureRuleService.java ====================
package com.example.demo.service;

import com.example.demo.model.TemperatureRule;
import java.time.LocalDate;
import java.util.List;

public interface TemperatureRuleService {
    TemperatureRule createRule(TemperatureRule rule);
    TemperatureRule updateRule(Long id, TemperatureRule rule);
    List<TemperatureRule> getActiveRules();
    TemperatureRule getRuleForProduct(String productType, LocalDate date);
    List<TemperatureRule> getAllRules();
}

// ==================== TemperatureRuleServiceImpl.java ====================
package com.example.demo.service;

import com.example.demo.model.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TemperatureRuleServiceImpl implements TemperatureRuleService {
    
    private final TemperatureRuleRepository repository;
    
    public TemperatureRuleServiceImpl(TemperatureRuleRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }
        return repository.save(rule);
    }
    
    @Override
    public TemperatureRule updateRule(Long id, TemperatureRule rule) {
        TemperatureRule existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
        
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }
        
        existing.setProductType(rule.getProductType());
        existing.setMinTemp(rule.getMinTemp());
        existing.setMaxTemp(rule.getMaxTemp());
        existing.setActive(rule.getActive());
        existing.setEffectiveFrom(rule.getEffectiveFrom());
        existing.setEffectiveTo(rule.getEffectiveTo());
        
        return repository.save(existing);
    }
    
    @Override
    public List<TemperatureRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
    
    @Override
    public TemperatureRule getRuleForProduct(String productType, LocalDate date) {
        return repository.findApplicableRule(productType, date);
    }
    
    @Override
    public List<TemperatureRule> getAllRules() {
        return repository.findAll();
    }
}