package com.example.demo.solar.parameters;

public class TaxRateParameters {
    private final double leaseTaxRate;
    private final double operationMaintenanceTaxRate;
    private final double inspectionTaxRate;
    private final double insuranceTaxRate;
    private final double disassemblyTaxRate;
    private final double managementTaxRate;
    private final double urbanConstructionTaxRate;
    private final double educationTaxRate;
    private final double localEducationTaxRate;
    private final double vatRate;
    private final double engineeringServiceTaxRate;
    private final double procurementTaxRate;
    private final double constructionTaxRate;

    public TaxRateParameters(double leaseTaxRate, double operationMaintenanceTaxRate, double inspectionTaxRate,
                             double insuranceTaxRate, double disassemblyTaxRate, double managementTaxRate,
                             double urbanConstructionTaxRate, double educationTaxRate, double localEducationTaxRate,
                             double vatRate, double engineeringServiceTaxRate, double procurementTaxRate,
                             double constructionTaxRate) {
        this.leaseTaxRate = leaseTaxRate;
        this.operationMaintenanceTaxRate = operationMaintenanceTaxRate;
        this.inspectionTaxRate = inspectionTaxRate;
        this.insuranceTaxRate = insuranceTaxRate;
        this.disassemblyTaxRate = disassemblyTaxRate;
        this.managementTaxRate = managementTaxRate;
        this.urbanConstructionTaxRate = urbanConstructionTaxRate;
        this.educationTaxRate = educationTaxRate;
        this.localEducationTaxRate = localEducationTaxRate;
        this.vatRate = vatRate;
        this.engineeringServiceTaxRate = engineeringServiceTaxRate;
        this.procurementTaxRate = procurementTaxRate;
        this.constructionTaxRate = constructionTaxRate;
    }

    public double getLeaseTaxRate() { return leaseTaxRate; }
    public double getOperationMaintenanceTaxRate() { return operationMaintenanceTaxRate; }
    public double getInspectionTaxRate() { return inspectionTaxRate; }
    public double getInsuranceTaxRate() { return insuranceTaxRate; }
    public double getDisassemblyTaxRate() { return disassemblyTaxRate; }
    public double getManagementTaxRate() { return managementTaxRate; }
    public double getUrbanConstructionTaxRate() { return urbanConstructionTaxRate; }
    public double getEducationTaxRate() { return educationTaxRate; }
    public double getLocalEducationTaxRate() { return localEducationTaxRate; }
    public double getVatRate() { return vatRate; }
    public double getEngineeringServiceTaxRate() { return engineeringServiceTaxRate; }
    public double getProcurementTaxRate() { return procurementTaxRate; }
    public double getConstructionTaxRate() { return constructionTaxRate; }
    public double getSurchargeRate() { return urbanConstructionTaxRate + educationTaxRate + localEducationTaxRate; }
}
