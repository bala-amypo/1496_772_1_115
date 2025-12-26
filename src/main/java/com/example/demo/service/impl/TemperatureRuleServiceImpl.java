package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TemperatureRuleServiceImpl implements TemperatureRuleService {

    private final TemperatureRuleRepository repo;

    public TemperatureRuleServiceImpl(TemperatureRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() > rule.getMaxTemp())
            throw new IllegalArgumentException("Min > Max");
        return repo.save(rule);
    }
    
    @Override
    public Optional<TemperatureRule> getRuleForProduct(String product, LocalDate date) {
        return repo.findApplicableRule(product, date);
    }

    @Override
    public List<TemperatureRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

        // ✅ NEW: Update rule
    @Override
    public TemperatureRule updateRule(Long id, TemperatureRule rule) {

        TemperatureRule existingRule = repo.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("TemperatureRule not found with id " + id)
                );

        if (rule.getMinTemp() > rule.getMaxTemp()) {
            throw new IllegalArgumentException("Min temperature cannot be greater than Max temperature");
        }

        existingRule.setProductType(rule.getProductType());
        existingRule.setMinTemp(rule.getMinTemp());
        existingRule.setMaxTemp(rule.getMaxTemp());
        existingRule.setActive(rule.isActive());
        existingRule.setEffectiveFrom(rule.getEffectiveFrom());
        existingRule.setEffectiveTo(rule.getEffectiveTo());

        return repo.save(existingRule);
    }

    // ✅ NEW: Get all rules
    @Override
    public List<TemperatureRule> getAllRules() {
        return repo.findAll();
    }

}
