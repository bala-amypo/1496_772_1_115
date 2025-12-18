package com.example.demo.service;

import com.example.demo.model.AlertRecord;
import java.util.List;

public interface AlertService {
    AlertRecord triggerAlert(AlertRecord alert);
    AlertRecord acknowledgeAlert(Long id);
    List<AlertRecord> getAlertsByShipment(Long shipmentId);
    List<AlertRecord> getAllAlerts();
}
// ==================== UserService.java ====================
package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User registerUser(User user);
    User findByEmail(String email);
}

// ==================== UserServiceImpl.java ====================
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}



