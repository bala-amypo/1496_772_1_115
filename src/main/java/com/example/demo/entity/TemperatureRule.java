package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import java.time.LocalDate;

@Entity
@Table(
    name = "temperature_rules",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "product_type",
            "effective_from",
            "active"
        }
    )
)
public class TemperatureRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_type")
    private String productType;

    @NotNull
    private Double minTemp;

    @NotNull
    private Double maxTemp;

    private Boolean active;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    // Constructors
    public TemperatureRule() {}

    public TemperatureRule(String productType, Double minTemp, Double maxTemp,
                           LocalDate effectiveFrom, LocalDate effectiveTo) {
        this.productType = productType;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.active = true;
    }

    /**
     * Rule: minTemp < maxTemp
     */
    @AssertTrue(message = "minTemp must be less than maxTemp")
    public boolean isTemperatureRangeValid() {
        if (minTemp == null || maxTemp == null) {
            return true; // handled by @NotNull
        }
        return minTemp < maxTemp;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public Double getMinTemp() { return minTemp; }
    public void setMinTemp(Double minTemp) { this.minTemp = minTemp; }

    public Double getMaxTemp() { return maxTemp; }
    public void setMaxTemp(Double maxTemp) { this.maxTemp = maxTemp; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
}
