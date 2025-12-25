package com.example.demo.service;

import com.example.demo.entity.ShipmentRecord;
import java.util.List;

public interface ShipmentRecordService {
    ShipmentRecord createShipment(ShipmentRecord shipment);
    ShipmentRecord updateShipmentStatus(Long id, String status);
    ShipmentRecord getShipmentByCode(String code);
    ShipmentRecord getShipmentById(Long id);
    List<ShipmentRecord> getAllShipments();
}
TEMPERATURESENSORLOG:
package com.example.demo.service;

import com.example.demo.model.TemperatureSensorLog;
import java.util.List;

public interface TemperatureLogService {
    TemperatureSensorLog recordLog(TemperatureSensorLog log);
    List<TemperatureSensorLog> getLogsByShipment(Long shipmentId);
    TemperatureSensorLog getLogById(Long id);
    List<TemperatureSensorLog> getAllLogs();
}
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
TEMPERATURERULE:
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
BREACH:
package com.example.demo.service;

import com.example.demo.model.BreachRecord;
import java.util.List;

public interface BreachDetectionService {
    BreachRecord logBreach(BreachRecord breach);
    List<BreachRecord> getBreachesByShipment(Long shipmentId);
    BreachRecord resolveBreach(Long id);
    BreachRecord getBreachById(Long id);
    List<BreachRecord> getAllBreaches();
}
package com.example.demo.service;

import com.example.demo.model.BreachRecord;
import com.example.demo.repository.BreachRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachDetectionServiceImpl implements BreachDetectionService {

    private final BreachRecordRepository repository;

    public BreachDetectionServiceImpl(BreachRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRecord logBreach(BreachRecord breach) {
        return repository.save(breach);
    }

    @Override
    public List<BreachRecord> getBreachesByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }

    @Override
    public BreachRecord resolveBreach(Long id) {
        BreachRecord breach = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach not found"));
        breach.setResolved(true);
        return repository.save(breach);
    }

    @Override
    public BreachRecord getBreachById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach not found"));
    }

    @Override
    public List<BreachRecord> getAllBreaches() {
        return repository.findAll();
    }
}
ALERT:
package com.example.demo.service;

import com.example.demo.model.AlertRecord;
import java.util.List;

public interface AlertService {

    AlertRecord triggerAlert(AlertRecord alert);

    AlertRecord acknowledgeAlert(Long id);

    List<AlertRecord> getAlertsByShipment(Long shipmentId);

    List<AlertRecord> getAllAlerts();
}
package com.example.demo.service;

import com.example.demo.model.AlertRecord;
import com.example.demo.repository.AlertRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRecordRepository repository;

    public AlertServiceImpl(AlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public AlertRecord triggerAlert(AlertRecord alert) {
        alert.setId(null); // ensure new record
        return repository.save(alert);
    }

    @Override
    public AlertRecord acknowledgeAlert(Long id) {
        AlertRecord alert = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found with id: " + id));

        alert.setAcknowledged(true);
        return repository.save(alert);
    }

    @Override
    public List<AlertRecord> getAlertsByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }

    @Override
    public List<AlertRecord> getAllAlerts() {
        return repository.findAll();
    }
}
USER:
package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
}
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // No password encoding in simple version
        return repository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = repository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return null; // Invalid credentials
        }
        return user;
    }
}
