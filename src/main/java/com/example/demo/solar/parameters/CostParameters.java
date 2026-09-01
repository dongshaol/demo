package com.example.demo.solar.parameters;

public class CostParameters {
    private final double roofLeaseAnnual;
    private final double operationMaintenanceAnnual;
    private final double inspectionAnnual;
    private final double insuranceAnnual;
    private final double roofMaintenanceAnnual;
    private final double managementAnnual;

    public CostParameters(double roofLeaseAnnual, double operationMaintenanceAnnual, double inspectionAnnual,
                          double insuranceAnnual, double roofMaintenanceAnnual, double managementAnnual) {
        this.roofLeaseAnnual = roofLeaseAnnual;
        this.operationMaintenanceAnnual = operationMaintenanceAnnual;
        this.inspectionAnnual = inspectionAnnual;
        this.insuranceAnnual = insuranceAnnual;
        this.roofMaintenanceAnnual = roofMaintenanceAnnual;
        this.managementAnnual = managementAnnual;
    }

    public double getRoofLeaseAnnual() { return roofLeaseAnnual; }
    public double getOperationMaintenanceAnnual() { return operationMaintenanceAnnual; }
    public double getInspectionAnnual() { return inspectionAnnual; }
    public double getInsuranceAnnual() { return insuranceAnnual; }
    public double getRoofMaintenanceAnnual() { return roofMaintenanceAnnual; }
    public double getManagementAnnual() { return managementAnnual; }
}
