package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureRuleServiceImpl implements TemperatureRuleService {

    private final TemperatureRuleRepository repo;

    public TemperatureRuleServiceImpl(TemperatureRuleRepository repo) {
        this.repo = repo;
    }

    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new IllegalArgumentException("minTemp must be less than maxTemp");
        }
        if (rule.getEffectiveTo().isBefore(rule.getEffectiveFrom())) {
            throw new IllegalArgumentException("Invalid effective date range");
        }
        return repo.save(rule);
    }

    public Optional<TemperatureRule> getRuleForProduct(String product, LocalDate date) {
        return repo.findApplicableRule(product, date);
    }

    public List<TemperatureRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
