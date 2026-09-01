package com.example.demo.solar.parameters;

public class BasicParameters {
    private final double projectScaleMw;
    private final int operationYears;
    private final int constructionMonths;
    private final double residualRate;

    public BasicParameters(double projectScaleMw, int operationYears, int constructionMonths, double residualRate) {
        this.projectScaleMw = projectScaleMw;
        this.operationYears = operationYears;
        this.constructionMonths = constructionMonths;
        this.residualRate = residualRate;
    }

    public double getProjectScaleMw() { return projectScaleMw; }
    public int getOperationYears() { return operationYears; }
    public int getConstructionMonths() { return constructionMonths; }
    public double getResidualRate() { return residualRate; }
}
