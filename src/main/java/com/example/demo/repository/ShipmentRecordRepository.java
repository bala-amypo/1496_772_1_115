// ==================== ShipmentRecordRepository.java ====================
package com.example.demo.repository;

import com.example.demo.model.ShipmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRecordRepository extends JpaRepository<ShipmentRecord, Long> {
    ShipmentRecord findByShipmentCode(String code);
}
