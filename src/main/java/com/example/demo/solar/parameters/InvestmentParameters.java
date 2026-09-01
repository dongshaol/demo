package com.example.demo.solar.parameters;

public class InvestmentParameters {
    private final double epcCostPerWatt;
    private final double engineeringRatio;
    private final double procurementRatio;
    private final double constructionRatio;
    private final double thirdPartyCostPerWatt;
    private final double discountRate;

    public InvestmentParameters(double epcCostPerWatt, double engineeringRatio, double procurementRatio,
                                double constructionRatio, double thirdPartyCostPerWatt, double discountRate) {
        this.epcCostPerWatt = epcCostPerWatt;
        this.engineeringRatio = engineeringRatio;
        this.procurementRatio = procurementRatio;
        this.constructionRatio = constructionRatio;
        this.thirdPartyCostPerWatt = thirdPartyCostPerWatt;
        this.discountRate = discountRate;
    }

    public double getEpcCostPerWatt() { return epcCostPerWatt; }
    public double getEngineeringRatio() { return engineeringRatio; }
    public double getProcurementRatio() { return procurementRatio; }
    public double getConstructionRatio() { return constructionRatio; }
    public double getThirdPartyCostPerWatt() { return thirdPartyCostPerWatt; }
    public double getDiscountRate() { return discountRate; }
}
