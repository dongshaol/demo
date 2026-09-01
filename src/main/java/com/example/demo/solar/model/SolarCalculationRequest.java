package com.example.demo.solar.model;

import com.example.demo.solar.parameters.*;

public class SolarCalculationRequest {
    private final BasicParameters basic;
    private final InvestmentParameters investment;
    private final CostParameters cost;
    private final GenerationParameters generation;
    private final PriceParameters price;
    private final FinanceParameters finance;
    private final TaxRateParameters taxRate;
    private final FixedParameters fixed;

    public SolarCalculationRequest(BasicParameters basic, InvestmentParameters investment, CostParameters cost,
                                   GenerationParameters generation, PriceParameters price, FinanceParameters finance,
                                   TaxRateParameters taxRate, FixedParameters fixed) {
        this.basic = basic;
        this.investment = investment;
        this.cost = cost;
        this.generation = generation;
        this.price = price;
        this.finance = finance;
        this.taxRate = taxRate;
        this.fixed = fixed;
    }

    public BasicParameters getBasic() { return basic; }
    public InvestmentParameters getInvestment() { return investment; }
    public CostParameters getCost() { return cost; }
    public GenerationParameters getGeneration() { return generation; }
    public PriceParameters getPrice() { return price; }
    public FinanceParameters getFinance() { return finance; }
    public TaxRateParameters getTaxRate() { return taxRate; }
    public FixedParameters getFixed() { return fixed; }

    public static SolarCalculationRequest excelExample() {
        return new SolarCalculationRequest(
                new BasicParameters(1.0, 25, 0, 0.0),
                new InvestmentParameters(2.8, 0.015, 0.52, 0.465, 0.15, 0.07),
                new CostParameters(0, 4, 0, 0.48, 0, 0),
                new GenerationParameters(1071.64, 0.99, 0.01, 0.004, 0),
                new PriceParameters(0.25, 0.36, 0, 0, 0),
                new FinanceParameters(0.30, 10, 0.03),
                new TaxRateParameters(0.05, 0.06, 0.06, 0.06, 0.09, 0.06,
                        0.07, 0.03, 0.02, 0.13, 0.06, 0.13, 0.09),
                new FixedParameters(0.25, 4, 11, 100, 0.02, 0.03)
        );
    }
}
