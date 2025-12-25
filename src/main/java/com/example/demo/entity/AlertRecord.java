package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_records")
public class AlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipmentId;
    private Long breachId;
    private boolean acknowledged;
    private LocalDateTime sentAt;

    public AlertRecord() {}

    public AlertRecord(Long shipmentId, Long breachId,
                       boolean acknowledged, LocalDateTime sentAt) {
        this.shipmentId = shipmentId;
        this.breachId = breachId;
        this.acknowledged = acknowledged;
        this.sentAt = sentAt;
    }

    @PrePersist
    public void prePersist() {
        this.acknowledged = false;
        this.sentAt = LocalDateTime.now();
    }

    // getters & setters
}
