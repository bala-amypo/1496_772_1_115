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