package com.example.demo.solar.parameters;

public class GenerationParameters {
    private final double annualGenerationHours;
    private final double generationEfficiency;
    private final double firstYearLossRate;
    private final double annualLossRate;
    private final double selfUseRatio;

    public GenerationParameters(double annualGenerationHours, double generationEfficiency,
                                double firstYearLossRate, double annualLossRate, double selfUseRatio) {
        this.annualGenerationHours = annualGenerationHours;
        this.generationEfficiency = generationEfficiency;
        this.firstYearLossRate = firstYearLossRate;
        this.annualLossRate = annualLossRate;
        this.selfUseRatio = selfUseRatio;
    }

    public double getAnnualGenerationHours() { return annualGenerationHours; }
    public double getGenerationEfficiency() { return generationEfficiency; }
    public double getFirstYearLossRate() { return firstYearLossRate; }
    public double getAnnualLossRate() { return annualLossRate; }
    public double getSelfUseRatio() { return selfUseRatio; }
    public double getGridRatio() { return 1.0 - selfUseRatio; }
}
