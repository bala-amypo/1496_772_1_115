package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ShipmentRecord;

@Service
public interface ShipmentRecordService {
    ShipmentRecord createShipment(ShipmentRecord shipment);
    ShipmentRecord updateShipmentStatus(long id,String status);
    Optional<ShipmentRecord> getShipmentByCode(String code);
    ShipmentRecord getShipmentById(long id);
    List<ShipmentRecord> getAllShipments();
    
}

