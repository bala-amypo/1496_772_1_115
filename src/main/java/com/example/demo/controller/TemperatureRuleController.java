package com.example.demo.controller;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Temperature Rules")
@SecurityRequirement(name = "bearerAuth")
public class TemperatureRuleController {

    private final TemperatureRuleService service;

    public TemperatureRuleController(TemperatureRuleService service) {
        this.service = service;
    }

    // ✅ POST /api/rules - Create rule
    @PostMapping
    public TemperatureRule create(@RequestBody TemperatureRule rule) {
        return service.createRule(rule);
    }

    // ✅ PUT /api/rules/{id} - Update rule
    @PutMapping("/{id}")
    public TemperatureRule update(
            @PathVariable Long id,
            @RequestBody TemperatureRule rule) {

        return service.updateRule(id, rule);
    }

    // ✅ GET /api/rules - List all rules
    @GetMapping
    public List<TemperatureRule> getAllRules() {
        return service.getAllRules();
    }

    // ✅ GET /api/rules/active - Get active rules
    @GetMapping("/active")
    public List<TemperatureRule> getActive() {
        return service.getActiveRules();
    }

    // ✅ GET /api/rules/product/{productType}
    // ✅ Optional date parameter
    @GetMapping("/product/{productType}")
    public Optional<TemperatureRule> getRuleByProduct(
            @PathVariable String productType,
            @RequestParam(required = false) LocalDate date) {

        LocalDate effectiveDate = (date != null) ? date : LocalDate.now();

        return service.getRuleForProduct(productType, effectiveDate);
    }
}
