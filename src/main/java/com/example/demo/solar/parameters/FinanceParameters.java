package com.example.demo.solar.parameters;

public class FinanceParameters {
    private final double equityRatio;
    private final int loanYears;
    private final double loanRate;

    public FinanceParameters(double equityRatio, int loanYears, double loanRate) {
        this.equityRatio = equityRatio;
        this.loanYears = loanYears;
        this.loanRate = loanRate;
    }

    public double getEquityRatio() { return equityRatio; }
    public int getLoanYears() { return loanYears; }
    public double getLoanRate() { return loanRate; }
}
