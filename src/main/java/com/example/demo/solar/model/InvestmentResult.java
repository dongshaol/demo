package com.example.demo.solar.model;

public class InvestmentResult {
    private final double epcTotal;
    private final double thirdPartyTotal;
    private final double projectTotal;
    private final double deductibleVat;
    private final double fixedAssetOriginalValue;
    private final double depreciationBase;
    private final double residualValue;
    private final double loanAmount;
    private final double equityAmount;

    public InvestmentResult(double epcTotal, double thirdPartyTotal, double projectTotal, double deductibleVat,
                            double fixedAssetOriginalValue, double depreciationBase, double residualValue,
                            double loanAmount, double equityAmount) {
        this.epcTotal = epcTotal;
        this.thirdPartyTotal = thirdPartyTotal;
        this.projectTotal = projectTotal;
        this.deductibleVat = deductibleVat;
        this.fixedAssetOriginalValue = fixedAssetOriginalValue;
        this.depreciationBase = depreciationBase;
        this.residualValue = residualValue;
        this.loanAmount = loanAmount;
        this.equityAmount = equityAmount;
    }

    public double getEpcTotal() { return epcTotal; }
    public double getThirdPartyTotal() { return thirdPartyTotal; }
    public double getProjectTotal() { return projectTotal; }
    public double getDeductibleVat() { return deductibleVat; }
    public double getFixedAssetOriginalValue() { return fixedAssetOriginalValue; }
    public double getDepreciationBase() { return depreciationBase; }
    public double getResidualValue() { return residualValue; }
    public double getLoanAmount() { return loanAmount; }
    public double getEquityAmount() { return equityAmount; }
}
