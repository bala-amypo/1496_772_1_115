package com.example.demo.entity;

import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

public class AlertRecord {

    private Long id;
    private Long shipmentId;
    private Long breachId;
    private Boolean acknowledged;
    private LocalDateTime sentAt;

    @PrePersist
    public void prePersist() {
        acknowledged = false;
        sentAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Long getBreachId() { return breachId; }
    public void setBreachId(Long breachId) { this.breachId = breachId; }

    public Boolean getAcknowledged() { return acknowledged; }
    public void setAcknowledged(Boolean acknowledged) { this.acknowledged = acknowledged; }

    public LocalDateTime getSentAt() { return sentAt; }
}
