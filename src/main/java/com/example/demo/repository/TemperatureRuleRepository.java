package com.example.demo.repository;

import com.example.demo.entity.TemperatureRule;
import org.springframework.data.jpa.repository.*;
import java.time.LocalDate;
import java.util.*;

public interface TemperatureRuleRepository extends JpaRepository<TemperatureRule, Long> {

    @Query("""
      SELECT r FROM TemperatureRule r
      WHERE r.productType = :product
        AND r.active = true
        AND :date BETWEEN r.effectiveFrom AND r.effectiveTo
    """)
    Optional<TemperatureRule> findApplicableRule(String product, LocalDate date);

    List<TemperatureRule> findByActiveTrue();
}
