package com.example.demo.repository;

import com.example.demo.entity.TemperatureRule;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TemperatureRuleRepository {

    TemperatureRule save(TemperatureRule rule);

    List<TemperatureRule> findByActiveTrue();

    Optional<TemperatureRule> findApplicableRule(String productType, LocalDate date);
}
