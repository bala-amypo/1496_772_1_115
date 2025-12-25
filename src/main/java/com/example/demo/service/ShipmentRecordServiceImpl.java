package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {

    private final ShipmentRecordRepository repository;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository repository) {
        this.repository = repository;
    }

    public ShipmentRecord createShipment(ShipmentRecord s) { return repository.save(s); }
    public ShipmentRecord updateShipmentStatus(Long id, String status) {
        ShipmentRecord s = repository.findById(id).orElseThrow();
        s.setStatus(status);
        return repository.save(s);
    }
    public ShipmentRecord getShipmentByCode(String code) { return repository.findByShipmentCode(code); }
    public ShipmentRecord getShipmentById(Long id) { return repository.findById(id).orElse(null); }
    public List<ShipmentRecord> getAllShipments() { return repository.findAll(); }
}


ALERT:

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
