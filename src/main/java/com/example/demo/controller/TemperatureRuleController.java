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
    
    @PostMapping
    @Operation(summary = "Create a new temperature rule")
    public ResponseEntity<TemperatureRule> createRule(@RequestBody TemperatureRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

  

}
