package com.example.demo.solar.controller;

import com.example.demo.solar.model.SolarCalculationRequest;
import com.example.demo.solar.model.SolarCalculationResult;
import com.example.demo.solar.service.SolarCalculationService;

public class SolarCalculationController {
    private final SolarCalculationService calculationService;

    public SolarCalculationController(SolarCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public SolarCalculationResult calculate(SolarCalculationRequest request) {
        return calculationService.calculate(request);
    }
}
