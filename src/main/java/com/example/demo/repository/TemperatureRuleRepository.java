package com.example.demo.repository;

import com.example.demo.model.TemperatureRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemperatureRuleRepository extends JpaRepository<TemperatureRule, Long> {
    
    List<TemperatureRule> findByActiveTrue();
    
    @Query("SELECT t FROM TemperatureRule t WHERE t.productType = :productType " +
           "AND t.active = true " +
           "AND t.effectiveFrom <= :date " +
           "AND t.effectiveTo >= :date")
    TemperatureRule findApplicableRule(
        @Param("productType") String productType, 
        @Param("date") LocalDate date
    );
}
