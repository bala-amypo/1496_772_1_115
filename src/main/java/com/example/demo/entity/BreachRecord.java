package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breach_records")
public class BreachRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipmentId;
    private Long logId;
    private Double breachValue;
    private String severity;
    private boolean resolved;

    public BreachRecord() {}

    public BreachRecord(Long shipmentId, Long logId,
                        Double breachValue, String severity, boolean resolved) {
        this.shipmentId = shipmentId;
        this.logId = logId;
        this.breachValue = breachValue;
        this.severity = severity;
        this.resolved = resolved;
    }

    @PrePersist
    public void prePersist() {
        this.resolved = false;
    }

    // getters & setters
}
