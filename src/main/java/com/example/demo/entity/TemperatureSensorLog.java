package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_sensor_logs")
public class TemperatureSensorLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long shipmentId;
    private String sensorId;
    private LocalDateTime recordedAt;
    private Double temperatureValue;
    private String location;
    
    @PrePersist
    protected void onCreate() {
        if (recordedAt == null) {
            recordedAt = LocalDateTime.now();
        }
    }
    
    // Constructors
    
    public TemperatureSensorLog(Long shipmentId, String sensorId, 
                               Double temperatureValue, String location) {
        this.shipmentId = shipmentId;
        this.sensorId = sensorId;
        this.temperatureValue = temperatureValue;
        this.location = location;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }
    
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
    
    public Double getTemperatureValue() { return temperatureValue; }
    public void setTemperatureValue(Double temperatureValue) { 
        this.temperatureValue = temperatureValue; 
    }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
