package com.example.demo.solar.model;

public class FinancialMetrics {
    private final double totalRevenueWithoutTax;
    private final double totalCostWithoutTax;
    private final double totalNetProfit;
    private final double projectPreTaxIrr;
    private final double projectPostTaxIrr;
    private final double equityPostTaxIrr;
    private final double projectPreTaxNpv;
    private final double projectPostTaxNpv;
    private final double staticPaybackYears;
    private final double dynamicPaybackYears;

    public FinancialMetrics(double totalRevenueWithoutTax, double totalCostWithoutTax, double totalNetProfit,
                            double projectPreTaxIrr, double projectPostTaxIrr, double equityPostTaxIrr,
                            double projectPreTaxNpv, double projectPostTaxNpv, double staticPaybackYears,
                            double dynamicPaybackYears) {
        this.totalRevenueWithoutTax = totalRevenueWithoutTax;
        this.totalCostWithoutTax = totalCostWithoutTax;
        this.totalNetProfit = totalNetProfit;
        this.projectPreTaxIrr = projectPreTaxIrr;
        this.projectPostTaxIrr = projectPostTaxIrr;
        this.equityPostTaxIrr = equityPostTaxIrr;
        this.projectPreTaxNpv = projectPreTaxNpv;
        this.projectPostTaxNpv = projectPostTaxNpv;
        this.staticPaybackYears = staticPaybackYears;
        this.dynamicPaybackYears = dynamicPaybackYears;
    }

    public double getTotalRevenueWithoutTax() { return totalRevenueWithoutTax; }
    public double getTotalCostWithoutTax() { return totalCostWithoutTax; }
    public double getTotalNetProfit() { return totalNetProfit; }
    public double getProjectPreTaxIrr() { return projectPreTaxIrr; }
    public double getProjectPostTaxIrr() { return projectPostTaxIrr; }
    public double getEquityPostTaxIrr() { return equityPostTaxIrr; }
    public double getProjectPreTaxNpv() { return projectPreTaxNpv; }
    public double getProjectPostTaxNpv() { return projectPostTaxNpv; }
    public double getStaticPaybackYears() { return staticPaybackYears; }
    public double getDynamicPaybackYears() { return dynamicPaybackYears; }
}
