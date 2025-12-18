package com.example.demo.repository;

import com.example.demo.entity.ShipmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRecordRepository extends JpaRepository<ShipmentRecord, Long> {
    ShipmentRecord findByShipmentCode(String code);
}
