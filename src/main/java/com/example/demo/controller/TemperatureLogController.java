package com.example.demo.controller;  // <-- your package declaration

// ----------- IMPORTS -----------
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List; // needed if using List
import com.example.demo.model.TemperatureSensorLog; // your entity
import com.example.demo.service.TemperatureLogService; // your service interface
// --------------------------------

@RestController
@RequestMapping("/temperature")
public class TemperatureLogController {

    private final TemperatureLogService temperatureLogService;

    public TemperatureLogController(TemperatureLogService temperatureLogService) {
        this.temperatureLogService = temperatureLogService;
    }

    @GetMapping
    public List<TemperatureSensorLog> getAllLogs() {
        return temperatureLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public TemperatureSensorLog getLogById(@PathVariable Long id) {
        return temperatureLogService.getLogById(id);
    }

    @PostMapping
    public TemperatureSensorLog saveLog(@RequestBody TemperatureSensorLog log) {
        return temperatureLogService.saveLog(log);
    }
}
