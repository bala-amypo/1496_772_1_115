package com.example.demo.entity;

import jakarta.persistence.PrePersist;

public class BreachRecord {

    private Long id;
    private Long shipmentId;
    private Long logId;
    private Double breachValue;
    private String severity;
    private Boolean resolved;

    @PrePersist
    public void prePersist() {
        resolved = false;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }

    public Double getBreachValue() { return breachValue; }
    public void setBreachValue(Double breachValue) { this.breachValue = breachValue; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
