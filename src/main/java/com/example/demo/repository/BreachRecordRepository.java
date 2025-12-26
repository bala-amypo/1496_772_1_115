package com.example.demo.repository;

import com.example.demo.entity.BreachRecord;
import java.util.List;
import java.util.Optional;

public interface BreachRecordRepository {

    BreachRecord save(BreachRecord breach);

    Optional<BreachRecord> findById(Long id);

    List<BreachRecord> findByShipmentId(Long shipmentId);
}
