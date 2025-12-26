package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BreachRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreachRecordRepository extends JpaRepository<BreachRecord, Long> {

    List<BreachRecord> findByShipmentId(Long shipmentId);
    Optional<BreachRecord> findById(Long id);
}
