package com.example.demo.repository;

import com.example.demo.entity.AlertRecord;
import java.util.List;

public interface AlertRecordRepository {

    AlertRecord save(AlertRecord alert);

    List<AlertRecord> findByShipmentId(Long shipmentId);
}
