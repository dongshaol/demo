package com.example.demo.solar.model;

import java.util.Collections;
import java.util.List;

public class SolarCalculationResult {
    private final InvestmentResult investment;
    private final List<AnnualResult> annualResults;
    private final FinancialMetrics metrics;

    public SolarCalculationResult(InvestmentResult investment, List<AnnualResult> annualResults,
                                  FinancialMetrics metrics) {
        this.investment = investment;
        this.annualResults = Collections.unmodifiableList(annualResults);
        this.metrics = metrics;
    }

    public InvestmentResult getInvestment() { return investment; }
    public List<AnnualResult> getAnnualResults() { return annualResults; }
    public FinancialMetrics getMetrics() { return metrics; }
}
