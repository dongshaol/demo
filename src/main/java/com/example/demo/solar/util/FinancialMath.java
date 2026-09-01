package com.example.demo.solar.util;

public final class FinancialMath {
    private FinancialMath() {
    }

    public static double npv(double rate, double[] cashFlows) {
        double result = 0;
        for (int i = 0; i < cashFlows.length; i++) {
            result += cashFlows[i] / Math.pow(1.0 + rate, i + 1);
        }
        return result;
    }

    public static double irr(double[] cashFlows) {
        double rate = 0.10;
        for (int iteration = 0; iteration < 100; iteration++) {
            double value = valueAtRate(cashFlows, rate);
            double derivative = derivativeAtRate(cashFlows, rate);
            if (Math.abs(value) < 1.0e-10) return rate;
            if (Math.abs(derivative) < 1.0e-12) break;
            double next = rate - value / derivative;
            if (next <= -0.999999 || !Double.isFinite(next)) break;
            if (Math.abs(next - rate) < 1.0e-12) return next;
            rate = next;
        }

        double previousRate = -0.90;
        double previousValue = valueAtRate(cashFlows, previousRate);
        for (int step = 1; step <= 20000; step++) {
            double currentRate = -0.90 + step * (10.90 / 20000.0);
            double currentValue = valueAtRate(cashFlows, currentRate);
            if (!sameSign(previousValue, currentValue)) {
                return bisect(cashFlows, previousRate, currentRate);
            }
            previousRate = currentRate;
            previousValue = currentValue;
        }
        return Double.NaN;
    }

    private static double bisect(double[] cashFlows, double low, double high) {
        double lowValue = valueAtRate(cashFlows, low);
        for (int i = 0; i < 200; i++) {
            double middle = (low + high) / 2.0;
            double middleValue = valueAtRate(cashFlows, middle);
            if (Math.abs(middleValue) < 1.0e-10) {
                return middle;
            }
            if (sameSign(lowValue, middleValue)) {
                low = middle;
                lowValue = middleValue;
            } else {
                high = middle;
            }
        }
        return (low + high) / 2.0;
    }

    public static double payback(double[] cashFlows, double discountRate) {
        double cumulative = 0;
        for (int i = 0; i < cashFlows.length; i++) {
            double cashFlow = discountRate == 0
                    ? cashFlows[i]
                    : cashFlows[i] / Math.pow(1.0 + discountRate, i + 1);
            double previous = cumulative;
            cumulative += cashFlow;
            if (cumulative >= 0 && previous < 0 && cashFlow > 0) {
                return i + (-previous / cashFlow);
            }
        }
        return Double.NaN;
    }

    private static double valueAtRate(double[] cashFlows, double rate) {
        double result = 0;
        for (int i = 0; i < cashFlows.length; i++) {
            result += cashFlows[i] / Math.pow(1.0 + rate, i);
        }
        return result;
    }

    private static double derivativeAtRate(double[] cashFlows, double rate) {
        double result = 0;
        for (int i = 1; i < cashFlows.length; i++) {
            result -= i * cashFlows[i] / Math.pow(1.0 + rate, i + 1);
        }
        return result;
    }

    private static boolean sameSign(double left, double right) {
        return (left >= 0 && right >= 0) || (left < 0 && right < 0);
    }
}
