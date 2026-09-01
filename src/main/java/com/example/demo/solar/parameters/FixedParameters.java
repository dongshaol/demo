package com.example.demo.solar.parameters;

public class FixedParameters {
    private final double incomeTaxRate;
    private final int inspectionLowRateStartYear;
    private final int inspectionHighRateStartYear;
    private final double inspectionCostBase;
    private final double inspectionLowRate;
    private final double inspectionHighRate;

    public FixedParameters(double incomeTaxRate, int inspectionLowRateStartYear, int inspectionHighRateStartYear,
                           double inspectionCostBase, double inspectionLowRate, double inspectionHighRate) {
        this.incomeTaxRate = incomeTaxRate;
        this.inspectionLowRateStartYear = inspectionLowRateStartYear;
        this.inspectionHighRateStartYear = inspectionHighRateStartYear;
        this.inspectionCostBase = inspectionCostBase;
        this.inspectionLowRate = inspectionLowRate;
        this.inspectionHighRate = inspectionHighRate;
    }

    public double getIncomeTaxRate() { return incomeTaxRate; }
    public int getInspectionLowRateStartYear() { return inspectionLowRateStartYear; }
    public int getInspectionHighRateStartYear() { return inspectionHighRateStartYear; }
    public double getInspectionCostBase() { return inspectionCostBase; }
    public double getInspectionLowRate() { return inspectionLowRate; }
    public double getInspectionHighRate() { return inspectionHighRate; }
}
