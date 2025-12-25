package com.example.demo.service;

import com.example.demo.entity.AlertRecord;

public interface AlertService {

    AlertRecord triggerAlert(AlertRecord alert);
}
