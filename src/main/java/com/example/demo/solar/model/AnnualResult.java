package com.example.demo.solar.model;

public class AnnualResult {
    private final int period;
    private final double componentEfficiency;
    private final double selfUsePower;
    private final double gridPower;
    private final double revenueWithTax;
    private final double revenueWithoutTax;
    private final double operatingCostWithTax;
    private final double operatingCostWithoutTax;
    private final double depreciation;
    private final double principal;
    private final double interest;
    private final double vatPayable;
    private final double taxSurcharge;
    private final double profitBeforeTax;
    private final double incomeTax;
    private final double netProfit;
    private final double projectPreTaxCashFlow;
    private final double projectPostTaxCashFlow;
    private final double equityPostTaxCashFlow;

    public AnnualResult(int period, double componentEfficiency, double selfUsePower, double gridPower,
                        double revenueWithTax, double revenueWithoutTax, double operatingCostWithTax,
                        double operatingCostWithoutTax, double depreciation, double principal, double interest,
                        double vatPayable, double taxSurcharge, double profitBeforeTax, double incomeTax,
                        double netProfit, double projectPreTaxCashFlow, double projectPostTaxCashFlow,
                        double equityPostTaxCashFlow) {
        this.period = period;
        this.componentEfficiency = componentEfficiency;
        this.selfUsePower = selfUsePower;
        this.gridPower = gridPower;
        this.revenueWithTax = revenueWithTax;
        this.revenueWithoutTax = revenueWithoutTax;
        this.operatingCostWithTax = operatingCostWithTax;
        this.operatingCostWithoutTax = operatingCostWithoutTax;
        this.depreciation = depreciation;
        this.principal = principal;
        this.interest = interest;
        this.vatPayable = vatPayable;
        this.taxSurcharge = taxSurcharge;
        this.profitBeforeTax = profitBeforeTax;
        this.incomeTax = incomeTax;
        this.netProfit = netProfit;
        this.projectPreTaxCashFlow = projectPreTaxCashFlow;
        this.projectPostTaxCashFlow = projectPostTaxCashFlow;
        this.equityPostTaxCashFlow = equityPostTaxCashFlow;
    }

    public int getPeriod() { return period; }
    public double getComponentEfficiency() { return componentEfficiency; }
    public double getSelfUsePower() { return selfUsePower; }
    public double getGridPower() { return gridPower; }
    public double getRevenueWithTax() { return revenueWithTax; }
    public double getRevenueWithoutTax() { return revenueWithoutTax; }
    public double getOperatingCostWithTax() { return operatingCostWithTax; }
    public double getOperatingCostWithoutTax() { return operatingCostWithoutTax; }
    public double getDepreciation() { return depreciation; }
    public double getPrincipal() { return principal; }
    public double getInterest() { return interest; }
    public double getVatPayable() { return vatPayable; }
    public double getTaxSurcharge() { return taxSurcharge; }
    public double getProfitBeforeTax() { return profitBeforeTax; }
    public double getIncomeTax() { return incomeTax; }
    public double getNetProfit() { return netProfit; }
    public double getProjectPreTaxCashFlow() { return projectPreTaxCashFlow; }
    public double getProjectPostTaxCashFlow() { return projectPostTaxCashFlow; }
    public double getEquityPostTaxCashFlow() { return equityPostTaxCashFlow; }
}
