package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert_records")
public class AlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long shipmentId;

    @NotNull
    private Long breachId;

    @NotBlank
    private String alertType;

    @NotBlank
    @Column(length = 500)
    private String message;

    private LocalDateTime sentAt;

    private Boolean acknowledged;

    @PrePersist
    protected void onCreate() {
        this.sentAt = LocalDateTime.now();
        if (this.acknowledged == null) {
            this.acknowledged = false;
        }
    }

    // Constructors
    public AlertRecord() {}

    public AlertRecord(Long shipmentId, Long breachId, String alertType, String message) {
        this.shipmentId = shipmentId;
        this.breachId = breachId;
        this.alertType = alertType;
        this.message = message;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Long getBreachId() { return breachId; }
    public void setBreachId(Long breachId) { this.breachId = breachId; }

    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getSentAt() { return sentAt; }

    public Boolean getAcknowledged() { return acknowledged; }
    public void setAcknowledged(Boolean acknowledged) { this.acknowledged = acknowledged; }
}
