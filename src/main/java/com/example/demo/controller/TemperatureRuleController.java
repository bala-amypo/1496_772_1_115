package com.example.demo.controller;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TemperatureRuleController {

    private final TemperatureRuleService ruleService;

    public TemperatureRuleController(TemperatureRuleService ruleService) {
        this.ruleService = ruleService;
    }

    public TemperatureRule createRule(TemperatureRule rule) {
        return ruleService.createRule(rule);
    }

    public Optional<TemperatureRule> getRule(String productType, LocalDate date) {
        return ruleService.getRuleForProduct(productType, date);
    }

    public List<TemperatureRule> getActiveRules() {
        return ruleService.getActiveRules();
    }
}
