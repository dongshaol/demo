package com.example.demo.solar;

import com.example.demo.solar.model.SolarCalculationRequest;
import com.example.demo.solar.model.SolarCalculationResult;
import com.example.demo.solar.service.SolarCalculationService;
import com.example.demo.solar.service.impl.SolarCalculationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SolarCalculationServiceTest {
    private final SolarCalculationService service = new SolarCalculationServiceImpl();

    @Test
    void reproducesCoreExcelExampleValues() {
        SolarCalculationResult result = service.calculate(SolarCalculationRequest.excelExample());

        assertEquals(280.0, result.getInvestment().getEpcTotal(), 1.0e-9);
        assertEquals(295.0, result.getInvestment().getProjectTotal(), 1.0e-9);
        assertEquals(28.5876936463, result.getInvestment().getDeductibleVat(), 1.0e-9);
        assertEquals(266.4123063537, result.getInvestment().getFixedAssetOriginalValue(), 1.0e-9);
        assertEquals(196.0, result.getInvestment().getLoanAmount(), 1.0e-9);
        assertEquals(99.0, result.getInvestment().getEquityAmount(), 1.0e-9);

        assertEquals(26, result.getAnnualResults().size());
        assertEquals(105.0314364, result.getAnnualResults().get(0).getGridPower(), 1.0e-7);
        assertEquals(552.75997300885, result.getMetrics().getTotalRevenueWithoutTax(), 1.0e-8);
        assertFalse(Double.isNaN(result.getMetrics().getProjectPostTaxIrr()));
    }
}
