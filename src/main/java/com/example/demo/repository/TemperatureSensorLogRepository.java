package com.example.demo.repository;

import com.example.demo.model.TemperatureSensorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSensorLogRepository extends JpaRepository<TemperatureSensorLog, Long> {
}
