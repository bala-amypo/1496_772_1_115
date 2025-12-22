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
        TemperatureRule savedRule = service.createRule(rule);
        return ResponseEntity.ok(savedRule);
    }

    @GetMapping
    @Operation(summary = "Get all temperature rules")
    public ResponseEntity<List<TemperatureRule>> getAllRules() {
        List<TemperatureRule> rules = service.getAllRules();
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active rules")
    public ResponseEntity<List<TemperatureRule>> getActiveRules() {
        List<TemperatureRule> rules = service.getActiveRules();
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/product")
    @Operation(summary = "Get rule for a specific product on a date")
    public ResponseEntity<TemperatureRule> getRuleForProduct(
            @RequestParam String productType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        TemperatureRule rule = service.getRuleForProduct(productType, date);
        if (rule == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(rule);
    }
    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve (deactivate) a temperature rule")
    public ResponseEntity<TemperatureRule> resolveRule(@PathVariable Long id) {
    return ResponseEntity.ok(service.resolveRule(id));
}

}
