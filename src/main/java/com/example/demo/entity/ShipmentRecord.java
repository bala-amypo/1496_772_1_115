package com.example.demo.entity;

import jakarta.persistence.PrePersist;

public class ShipmentRecord {

    private Long id;
    private String shipmentCode;
    private String origin;
    private String destination;
    private String status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "IN_TRANSIT";
        }
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getShipmentCode() { return shipmentCode; }
    public void setShipmentCode(String shipmentCode) { this.shipmentCode = shipmentCode; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
