package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShipmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shipmentCode;
    private String origin;
    private String destination;
    private String productType;
    private LocalDateTime startDate;
    private LocalDateTime expectedDelivery;
    private String status;
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = "IN_TRANSIT";
    }

    // getters & setters
}
