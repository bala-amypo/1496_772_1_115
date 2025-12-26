package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import com.example.demo.service.TemperatureRuleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TemperatureRuleServiceImpl implements TemperatureRuleService {

    private final TemperatureRuleRepository repository;

    public TemperatureRuleServiceImpl(TemperatureRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() == null || rule.getMaxTemp() == null ||
            rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new IllegalArgumentException("minTemp must be less than maxTemp");
        }
        return repository.save(rule);
    }

    @Override
    public Optional<TemperatureRule> getRuleForProduct(String productType, LocalDate date) {
        return repository.findApplicableRule(productType, date);
    }

    @Override
    public List<TemperatureRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}
