package com.example.demo.repository;

import com.example.demo.entity.ShipmentRecord;
import java.util.List;
import java.util.Optional;

public interface ShipmentRecordRepository {

    ShipmentRecord save(ShipmentRecord shipment);

    Optional<ShipmentRecord> findById(Long id);

    Optional<ShipmentRecord> findByShipmentCode(String shipmentCode);

    List<ShipmentRecord> findAll();
}
