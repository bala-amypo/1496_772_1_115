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
    private Boolean resolved;

    public BreachRecord() {}

    public BreachRecord(Long shipmentId, Long logId,
                        Double breachValue, String severity, Boolean resolved) {
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

    public Long getId() { return id; }
    public Long getShipmentId() { return shipmentId; }
    public Long getLogId() { return logId; }
    public Double getBreachValue() { return breachValue; }
    public String getSeverity() { return severity; }
    public Boolean getResolved() { return resolved; }

    public void setId(Long id) { this.id = id; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }
    public void setLogId(Long logId) { this.logId = logId; }
    public void setBreachValue(Double breachValue) { this.breachValue = breachValue; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
