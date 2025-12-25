package com.example.demo.service;

import com.example.demo.entity.AlertRecord;
import com.example.demo.repository.AlertRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRecordRepository repo;

    public AlertServiceImpl(AlertRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public AlertRecord triggerAlert(AlertRecord alert) {
        return repo.save(alert);
    }
}
