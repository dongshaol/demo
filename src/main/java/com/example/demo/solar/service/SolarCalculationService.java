package com.example.demo.solar.service;

import com.example.demo.solar.model.SolarCalculationRequest;
import com.example.demo.solar.model.SolarCalculationResult;

public interface SolarCalculationService {
    SolarCalculationResult calculate(SolarCalculationRequest request);
}
