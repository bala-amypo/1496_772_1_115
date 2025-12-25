package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "temperature_rules")
public class TemperatureRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productType;
    private Double minTemp;
    private Double maxTemp;
    private boolean active;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    public TemperatureRule() {}

    public TemperatureRule(String productType, Double minTemp, Double maxTemp,
                           boolean active, LocalDate effectiveFrom, LocalDate effectiveTo) {
        this.productType = productType;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.active = active;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
    }

    // getters & setters
}
