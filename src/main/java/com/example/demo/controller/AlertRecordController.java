TEMPERATURELOG:
package com.example.demo.controller;

import com.example.demo.model.TemperatureSensorLog;
import com.example.demo.service.TemperatureLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Temperature Logs", description = "Temperature log management")
public class TemperatureLogController {
    
    private final TemperatureLogService service;
    
    public TemperatureLogController(TemperatureLogService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Record a temperature log")
    public ResponseEntity<TemperatureSensorLog> recordLog(@RequestBody TemperatureSensorLog log) {
        return ResponseEntity.ok(service.recordLog(log));
    }
    
    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get logs for a shipment")
    public ResponseEntity<List<TemperatureSensorLog>> getLogsByShipment(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.getLogsByShipment(shipmentId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get log by ID")
    public ResponseEntity<TemperatureSensorLog> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLogById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all logs")
    public ResponseEntity<List<TemperatureSensorLog>> getAllLogs() {
        return ResponseEntity.ok(service.getAllLogs());
    }
}
TEMPERATURERULE:
package com.example.demo.controller;

import com.example.demo.model.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Temperature Rules", description = "Temperature rule management")
public class TemperatureRuleController {

    private final TemperatureRuleService service;

    public TemperatureRuleController(TemperatureRuleService service) {
        this.service = service;
    }

    // Create a new temperature rule
    @PostMapping
    @Operation(summary = "Create a new temperature rule")
    public ResponseEntity<TemperatureRule> createRule(@RequestBody TemperatureRule rule) {
        TemperatureRule savedRule = service.createRule(rule);
        return ResponseEntity.ok(savedRule);
    }

    // Get all rules
    @GetMapping
    @Operation(summary = "Get all temperature rules")
    public ResponseEntity<List<TemperatureRule>> getAllRules() {
        List<TemperatureRule> rules = service.getAllRules();
        return ResponseEntity.ok(rules);
    }

    // Get all active rules
    @GetMapping("/active")
    @Operation(summary = "Get all active rules")
    public ResponseEntity<List<TemperatureRule>> getActiveRules() {
        List<TemperatureRule> rules = service.getActiveRules();
        return ResponseEntity.ok(rules);
    }

    // Get rule for specific product and date
    @GetMapping("/product")
    @Operation(summary = "Get rule for a specific product on a date")
    public ResponseEntity<TemperatureRule> getRuleForProduct(
            @RequestParam String productType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        TemperatureRule rule = service.getRuleForProduct(productType, date);
        if (rule == null) {
            return ResponseEntity.notFound().build(); // returns 404 if no rule
        }
        return ResponseEntity.ok(rule);
    }
}
BREACH:
package com.example.demo.controller;

import com.example.demo.model.BreachRecord;
import com.example.demo.service.BreachDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breaches")
@Tag(name = "Breach Records", description = "Breach detection management")
public class BreachRecordController {

    private final BreachDetectionService service;

    public BreachRecordController(BreachDetectionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Log a new breach")
    public ResponseEntity<BreachRecord> logBreach(@RequestBody BreachRecord breach) {
        return ResponseEntity.ok(service.logBreach(breach));
    }

    @GetMapping
    @Operation(summary = "Get all breaches")
    public ResponseEntity<List<BreachRecord>> getAllBreaches() {
        return ResponseEntity.ok(service.getAllBreaches());
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get breaches by shipment ID")
    public ResponseEntity<List<BreachRecord>> getBreachesByShipment(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(service.getBreachesByShipment(shipmentId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get breach by ID")
    public ResponseEntity<BreachRecord> getBreachById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBreachById(id));
    }

    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve a breach")
    public ResponseEntity<BreachRecord> resolveBreach(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolveBreach(id));
    }
}
ALERT:
package com.example.demo.controller;

import com.example.demo.model.AlertRecord;
import com.example.demo.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alerts", description = "Alert management operations")
public class AlertRecordController {

    private final AlertService alertService;

    public AlertRecordController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    @Operation(summary = "Trigger a new alert")
    public ResponseEntity<AlertRecord> triggerAlert(
            @RequestBody AlertRecord alertRecord) {

        return ResponseEntity.ok(alertService.triggerAlert(alertRecord));
    }

    @PutMapping("/{id}/acknowledge")
    @Operation(summary = "Acknowledge an alert")
    public ResponseEntity<AlertRecord> acknowledgeAlert(
            @PathVariable Long id) {

        return ResponseEntity.ok(alertService.acknowledgeAlert(id));
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Get alerts by shipment ID")
    public ResponseEntity<List<AlertRecord>> getAlertsByShipment(
            @PathVariable Long shipmentId) {

        return ResponseEntity.ok(alertService.getAlertsByShipment(shipmentId));
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<List<AlertRecord>> getAllAlerts() {

        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
AUTH:
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("userId", savedUser.getId());
            response.put("email", savedUser.getEmail());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userService.loginUser(email, password);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
