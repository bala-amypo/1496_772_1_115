package com.example.demo.entity;

import java.time.LocalDateTime;

public class TemperatureSensorLog {

    private Long id;
    private Long shipmentId;
    private Double temperatureValue;
    private LocalDateTime recordedAt;
    private String location;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Double getTemperatureValue() { return temperatureValue; }
    public void setTemperatureValue(Double temperatureValue) { this.temperatureValue = temperatureValue; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
