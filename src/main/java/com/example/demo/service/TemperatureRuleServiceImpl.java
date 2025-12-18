package com.example.demo.service;

import com.example.demo.model.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TemperatureRuleServiceImpl implements TemperatureRuleService {
    
    private final TemperatureRuleRepository repository;
    
    public TemperatureRuleServiceImpl(TemperatureRuleRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }
        return repository.save(rule);
    }
    
    @Override
    public TemperatureRule updateRule(Long id, TemperatureRule rule) {
        TemperatureRule existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
        
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }
        
        existing.setProductType(rule.getProductType());
        existing.setMinTemp(rule.getMinTemp());
        existing.setMaxTemp(rule.getMaxTemp());
        existing.setActive(rule.getActive());
        existing.setEffectiveFrom(rule.getEffectiveFrom());
        existing.setEffectiveTo(rule.getEffectiveTo());
        
        return repository.save(existing);
    }
    
    @Override
    public List<TemperatureRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
    
    @Override
    public TemperatureRule getRuleForProduct(String productType, LocalDate date) {
        return repository.findApplicableRule(productType, date);
    }
    
    @Override
    public List<TemperatureRule> getAllRules() {
        return repository.findAll();
    }
}