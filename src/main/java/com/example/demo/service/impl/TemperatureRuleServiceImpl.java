package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import com.example.demo.service.TemperatureRuleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TemperatureRuleServiceImpl implements TemperatureRuleService {

    private final TemperatureRuleRepository ruleRepo;

    public TemperatureRuleServiceImpl(TemperatureRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new IllegalArgumentException("minTemp must be less than maxTemp");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public Optional<TemperatureRule> getRuleForProduct(String productType, LocalDate date) {
        return ruleRepo.findApplicableRule(productType, date);
    }

    @Override
    public List<TemperatureRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }
}
