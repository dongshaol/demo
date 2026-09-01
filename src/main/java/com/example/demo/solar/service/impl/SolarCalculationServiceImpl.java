package com.example.demo.solar.service.impl;

import com.example.demo.solar.model.*;
import com.example.demo.solar.parameters.*;
import com.example.demo.solar.service.SolarCalculationService;
import com.example.demo.solar.util.FinancialMath;

import java.util.ArrayList;
import java.util.List;

public class SolarCalculationServiceImpl implements SolarCalculationService {
    @Override
    public SolarCalculationResult calculate(SolarCalculationRequest request) {
        validate(request);
        BasicParameters basic = request.getBasic();
        InvestmentParameters investment = request.getInvestment();
        CostParameters cost = request.getCost();
        GenerationParameters generation = request.getGeneration();
        PriceParameters price = request.getPrice();
        FinanceParameters finance = request.getFinance();
        TaxRateParameters tax = request.getTaxRate();
        FixedParameters fixed = request.getFixed();

        InvestmentResult investmentResult = calculateInvestment(basic, investment, finance, tax);
        int periods = basic.getOperationYears() + 1;
        List<AnnualResult> annualResults = new ArrayList<AnnualResult>(periods);
        double[] preTaxCashFlows = new double[periods];
        double[] postTaxCashFlows = new double[periods];
        double[] equityCashFlows = new double[periods];
        double vatCreditBalance = 0;
        double totalRevenueWithoutTax = 0;
        double totalCostWithoutTax = 0;
        double totalNetProfit = 0;

        for (int index = 0; index < periods; index++) {
            int period = index + 1;
            double activityFactor = activityFactor(period, periods, basic.getConstructionMonths());
            double componentEfficiency = Math.max(0,
                    1.0 - generation.getFirstYearLossRate() - generation.getAnnualLossRate() * index);
            double totalPower = basic.getProjectScaleMw() * generation.getAnnualGenerationHours()
                    * generation.getGenerationEfficiency() * componentEfficiency * activityFactor / 10.0;
            double selfUsePower = totalPower * generation.getSelfUseRatio();
            double gridPower = totalPower * generation.getGridRatio();
            double subsidy = price.getTotalSubsidy();
            double revenueWithTax = selfUsePower * (price.getSelfUsePrice() + subsidy)
                    + gridPower * (price.getGridPrice() + subsidy);
            double revenueWithoutTax = revenueWithTax / (1.0 + tax.getVatRate());
            double outputVat = revenueWithTax - revenueWithoutTax;

            CostValues costs = calculateCosts(period, activityFactor, cost, fixed, tax);
            double depreciation = investmentResult.getDepreciationBase() / basic.getOperationYears() * activityFactor;
            LoanValues loan = calculateLoan(period, investmentResult.getLoanAmount(), finance);
            double totalCost = costs.withoutTax + depreciation + loan.interest;

            double currentVatBalance = costs.inputVat - outputVat;
            if (period == 1) {
                currentVatBalance += investmentResult.getDeductibleVat();
            } else if (vatCreditBalance > 0) {
                currentVatBalance += vatCreditBalance;
            }
            vatCreditBalance = currentVatBalance;
            double vatPayable = Math.max(0, -currentVatBalance);
            double surcharge = vatPayable * tax.getSurchargeRate();

            double profitBeforeTax = revenueWithoutTax - totalCost - surcharge;
            double incomeTax = Math.max(0, profitBeforeTax) * fixed.getIncomeTaxRate();
            double netProfit = profitBeforeTax - incomeTax;
            double adjustedIncomeTax = Math.max(0, profitBeforeTax + loan.interest) * fixed.getIncomeTaxRate();

            double terminalResidual = period == periods ? investmentResult.getResidualValue() : 0;
            double initialProjectInvestment = period == 1 ? investmentResult.getProjectTotal() : 0;
            double initialEquityInvestment = period == 1 ? investmentResult.getEquityAmount() : 0;
            double projectPreTaxCashFlow = revenueWithTax - costs.withTax - vatPayable - surcharge
                    - initialProjectInvestment + terminalResidual;
            double projectPostTaxCashFlow = projectPreTaxCashFlow - adjustedIncomeTax;
            double equityPostTaxCashFlow = revenueWithTax - costs.withTax - vatPayable - surcharge
                    - loan.principal - loan.interest - incomeTax - initialEquityInvestment + terminalResidual;

            preTaxCashFlows[index] = projectPreTaxCashFlow;
            postTaxCashFlows[index] = projectPostTaxCashFlow;
            equityCashFlows[index] = equityPostTaxCashFlow;
            totalRevenueWithoutTax += revenueWithoutTax;
            totalCostWithoutTax += totalCost;
            totalNetProfit += netProfit;

            annualResults.add(new AnnualResult(period, componentEfficiency, selfUsePower, gridPower,
                    revenueWithTax, revenueWithoutTax, costs.withTax, costs.withoutTax, depreciation,
                    loan.principal, loan.interest, vatPayable, surcharge, profitBeforeTax, incomeTax,
                    netProfit, projectPreTaxCashFlow, projectPostTaxCashFlow, equityPostTaxCashFlow));
        }

        FinancialMetrics metrics = new FinancialMetrics(totalRevenueWithoutTax, totalCostWithoutTax,
                totalNetProfit, FinancialMath.irr(preTaxCashFlows), FinancialMath.irr(postTaxCashFlows),
                FinancialMath.irr(equityCashFlows), FinancialMath.npv(investment.getDiscountRate(), preTaxCashFlows),
                FinancialMath.npv(investment.getDiscountRate(), postTaxCashFlows),
                FinancialMath.payback(postTaxCashFlows, 0),
                FinancialMath.payback(postTaxCashFlows, investment.getDiscountRate()));
        return new SolarCalculationResult(investmentResult, annualResults, metrics);
    }

    private InvestmentResult calculateInvestment(BasicParameters basic, InvestmentParameters investment,
                                                 FinanceParameters finance, TaxRateParameters tax) {
        double epcTotal = investment.getEpcCostPerWatt() * basic.getProjectScaleMw() * 100.0;
        double engineering = epcTotal * investment.getEngineeringRatio();
        double procurement = epcTotal * investment.getProcurementRatio();
        double construction = epcTotal * investment.getConstructionRatio();
        double epcVat = deductibleVat(engineering, tax.getEngineeringServiceTaxRate())
                + deductibleVat(procurement, tax.getProcurementTaxRate())
                + deductibleVat(construction, tax.getConstructionTaxRate());
        double thirdParty = investment.getThirdPartyCostPerWatt() * basic.getProjectScaleMw() * 100.0;
        double thirdPartyVat = deductibleVat(thirdParty, tax.getEngineeringServiceTaxRate());
        double projectTotal = epcTotal + thirdParty;
        double totalVat = epcVat + thirdPartyVat;
        double fixedAsset = projectTotal - totalVat;
        double depreciationBase = fixedAsset * (1.0 - basic.getResidualRate());
        double residualValue = fixedAsset * basic.getResidualRate();
        // 按源表口径：贷款基数为 EPC 总投资，不含第三方费用。
        double loan = epcTotal * (1.0 - finance.getEquityRatio());
        return new InvestmentResult(epcTotal, thirdParty, projectTotal, totalVat, fixedAsset,
                depreciationBase, residualValue, loan, projectTotal - loan);
    }

    private CostValues calculateCosts(int period, double factor, CostParameters cost, FixedParameters fixed,
                                      TaxRateParameters tax) {
        double lease = cost.getRoofLeaseAnnual() * factor;
        double operation = cost.getOperationMaintenanceAnnual() * factor;
        double inspection;
        if (period >= fixed.getInspectionHighRateStartYear()) {
            inspection = fixed.getInspectionCostBase() * fixed.getInspectionHighRate();
        } else if (period >= fixed.getInspectionLowRateStartYear()) {
            inspection = fixed.getInspectionCostBase() * fixed.getInspectionLowRate();
        } else {
            inspection = cost.getInspectionAnnual() * factor;
        }
        double insurance = cost.getInsuranceAnnual() * factor;
        double roofMaintenance = cost.getRoofMaintenanceAnnual() * factor;
        double management = cost.getManagementAnnual() * factor;
        double withTax = lease + operation + inspection + insurance + roofMaintenance + management;
        double withoutTax = netOfTax(lease, tax.getLeaseTaxRate())
                + netOfTax(operation, tax.getOperationMaintenanceTaxRate())
                + netOfTax(inspection, tax.getInspectionTaxRate())
                + netOfTax(insurance, tax.getInsuranceTaxRate())
                + netOfTax(roofMaintenance, tax.getDisassemblyTaxRate())
                + netOfTax(management, tax.getManagementTaxRate());
        return new CostValues(withTax, withoutTax, withTax - withoutTax);
    }

    private LoanValues calculateLoan(int period, double loanAmount, FinanceParameters finance) {
        int loanYear = period - 1;
        if (loanYear < 1 || loanYear > finance.getLoanYears()) {
            return new LoanValues(0, 0);
        }
        double annualPrincipal = loanAmount / finance.getLoanYears();
        double openingBalance = Math.max(0, loanAmount - annualPrincipal * (loanYear - 1));
        double principal = Math.min(annualPrincipal, openingBalance);
        double interest = (openingBalance - principal / 2.0) * finance.getLoanRate();
        return new LoanValues(principal, interest);
    }

    private double activityFactor(int period, int periods, int constructionMonths) {
        if (period == 1) return (12.0 - constructionMonths) / 12.0;
        if (period == periods) return constructionMonths / 12.0;
        return 1.0;
    }

    private double deductibleVat(double amount, double rate) { return amount * rate / (1.0 + rate); }
    private double netOfTax(double amount, double rate) { return amount / (1.0 + rate); }

    private void validate(SolarCalculationRequest request) {
        if (request == null || request.getBasic() == null || request.getInvestment() == null
                || request.getCost() == null || request.getGeneration() == null || request.getPrice() == null
                || request.getFinance() == null || request.getTaxRate() == null || request.getFixed() == null) {
            throw new IllegalArgumentException("参数组不能为空");
        }
        if (request.getBasic().getProjectScaleMw() <= 0 || request.getBasic().getOperationYears() <= 0) {
            throw new IllegalArgumentException("项目规模和运营周期必须大于 0");
        }
        if (request.getBasic().getConstructionMonths() < 0 || request.getBasic().getConstructionMonths() > 12) {
            throw new IllegalArgumentException("建设周期必须在 0 到 12 个月之间");
        }
        if (request.getFinance().getLoanYears() <= 0) {
            throw new IllegalArgumentException("贷款期限必须大于 0");
        }
    }

    private static class CostValues {
        private final double withTax;
        private final double withoutTax;
        private final double inputVat;
        private CostValues(double withTax, double withoutTax, double inputVat) {
            this.withTax = withTax;
            this.withoutTax = withoutTax;
            this.inputVat = inputVat;
        }
    }

    private static class LoanValues {
        private final double principal;
        private final double interest;
        private LoanValues(double principal, double interest) {
            this.principal = principal;
            this.interest = interest;
        }
    }
}
