package com.example.demo.solar.parameters;

public class PriceParameters {
    private final double gridPrice;
    private final double selfUsePrice;
    private final double nationalSubsidy;
    private final double provincialSubsidy;
    private final double municipalSubsidy;

    public PriceParameters(double gridPrice, double selfUsePrice, double nationalSubsidy,
                           double provincialSubsidy, double municipalSubsidy) {
        this.gridPrice = gridPrice;
        this.selfUsePrice = selfUsePrice;
        this.nationalSubsidy = nationalSubsidy;
        this.provincialSubsidy = provincialSubsidy;
        this.municipalSubsidy = municipalSubsidy;
    }

    public double getGridPrice() { return gridPrice; }
    public double getSelfUsePrice() { return selfUsePrice; }
    public double getTotalSubsidy() { return nationalSubsidy + provincialSubsidy + municipalSubsidy; }
}
