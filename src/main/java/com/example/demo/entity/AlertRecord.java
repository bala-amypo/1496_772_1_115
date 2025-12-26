package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

@Entity
@Table(name = "alert_records")
public class AlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_id", nullable = false)
    private Long shipmentId;

    @Column(name = "breach_id", nullable = false)
    private Long breachId;

    @Column(name = "alert_type", nullable = false)
    private String alertType;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "acknowledged", nullable = false)
    private Boolean acknowledged;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    public AlertRecord() {
    }

    public AlertRecord(Long shipmentId, Long breachId, String alertType, String message, LocalDateTime sentAt, Boolean acknowledged) {
        this.shipmentId = shipmentId;
        this.breachId = breachId;
        this.alertType = alertType;
        this.message = message;
        this.sentAt = sentAt;
        this.acknowledged = acknowledged;
    }

    @PrePersist
    public void prePersist() {
        
        if (this.acknowledged == null) {
            this.acknowledged = false; 
        }
        if (this.sentAt == null) {
            this.sentAt = LocalDateTime.now(); 
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getBreachId() {
        return breachId;
    }

    public void setBreachId(Long breachId) {
        this.breachId = breachId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}