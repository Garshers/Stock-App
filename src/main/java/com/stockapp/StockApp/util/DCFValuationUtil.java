package com.stockapp.StockApp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Discounted Cash Flow (DCF) valuation calculations.
 */
public class DCFValuationUtil {

    /**
     * Calculates the Discounted Cash Flow (DCF) value of a company or project.
     *
     * @param freeCashFlows A list of projected free cash flows for each period.
     * @param discountRate  The discount rate used to calculate the present value of future cash flows.
     * @param terminalValue The terminal value of the company or project at the end of the forecast period.
     * @return The calculated DCF value.
     */
    public BigDecimal calculateDCF(List<BigDecimal> freeCashFlows, BigDecimal discountRate, BigDecimal terminalValue) {
        BigDecimal presentValue = BigDecimal.ZERO;

        for (int i = 0; i < freeCashFlows.size(); i++) {
            BigDecimal divisor = BigDecimal.ONE.add(discountRate).pow(i + 1);
            presentValue = presentValue.add(freeCashFlows.get(i).divide(divisor, 10, RoundingMode.HALF_UP));
        }

        BigDecimal divisor = BigDecimal.ONE.add(discountRate).pow(freeCashFlows.size());
        presentValue = presentValue.add(terminalValue.divide(divisor, 10, RoundingMode.HALF_UP));

        return presentValue;
    }

    /**
     * Calculates the price per share using the Discounted Cash Flow (DCF) method.
     * This method incorporates a phased growth model, allowing for different growth rates
     * during the forecast period. It calculates the present value of future free cash flows,
     * including a terminal value calculated using the Gordon Growth Model, and then
     * derives the price per share by subtracting net debt and dividing by the number
     * of outstanding shares.
     *
     * @param lastYearFCF       The free cash flow from the last year.
     * @param initialGrowthRate The initial annual growth rate of free cash flow (e.g., 0.25 for 25%).
     * @param terminalGrowthRate The long-term, sustainable annual growth rate of free cash flow (e.g., 0.03 for 3%).
     * @param highGrowthYears   The number of years for which the initial growth rate is applied.
     * @param forecastYears     The total number of years for which free cash flows are projected.
     * @param discountRate      The discount rate used to calculate the present value of future cash flows.
     * @param numberOfShares    The number of outstanding shares.
     * @param netDebt           The net debt of the company (total debt minus cash and cash equivalents).
     * @return The calculated price per share.
     * @throws ArithmeticException If the discount rate is less than or equal to either the initial or terminal growth rate
     *                             (as this would lead to a division by zero or negative value error in the Gordon Growth Model).
     */
    public BigDecimal calculateDCF(BigDecimal lastYearFCF, BigDecimal initialGrowthRate, BigDecimal terminalGrowthRate, int highGrowthYears, int forecastYears, BigDecimal discountRate, long numberOfShares, BigDecimal netDebt) {

        if (discountRate.compareTo(initialGrowthRate) <= 0 || discountRate.compareTo(terminalGrowthRate) <= 0) {
            throw new ArithmeticException("Discount rate must be greater than both initial and terminal growth rates.");
        }

        List<BigDecimal> freeCashFlows = new ArrayList<>();
        BigDecimal currentFCF = lastYearFCF;
        BigDecimal sumOfProjectedCashFlows = BigDecimal.ZERO;

        // Project free cash flows for the forecast period (Not Discounted Cash Flow for forcast years)
        for (int i = 1; i <= forecastYears; i++) {
            BigDecimal growthRate = (i <= highGrowthYears) ? initialGrowthRate : terminalGrowthRate;
            currentFCF = currentFCF.multiply(BigDecimal.ONE.add(growthRate));
            freeCashFlows.add(currentFCF);
            sumOfProjectedCashFlows = sumOfProjectedCashFlows.add(currentFCF);
        }

        System.out.println("Free Cash Flow Prediction: " + freeCashFlows); // Log
        System.out.println("Free Cash Flow Sum: " + sumOfProjectedCashFlows); // Log

        BigDecimal presentValue = BigDecimal.ZERO;

        // Calculate the present value of the projected free cash flows (Discounted Cash Flow for forcast years)
        for (int i = 0; i < freeCashFlows.size(); i++) {
            BigDecimal divisor = BigDecimal.ONE.add(discountRate).pow(i + 1);
            presentValue = presentValue.add(freeCashFlows.get(i).divide(divisor, 10, RoundingMode.HALF_UP));
        }

        System.out.println("Present Value of Projected FCFs: " + presentValue); // Log

        // Calculate the terminal value using the Gordon Growth Model (Cash Flow beyond forcast years)
        BigDecimal terminalValue = freeCashFlows.get(freeCashFlows.size() - 1).multiply(BigDecimal.ONE.add(terminalGrowthRate)).divide(discountRate.subtract(terminalGrowthRate), 10, RoundingMode.HALF_UP);
        BigDecimal terminalValueDivisor = BigDecimal.ONE.add(discountRate).pow(forecastYears);
        terminalValue = terminalValue.divide(terminalValueDivisor, 10, RoundingMode.HALF_UP);

        presentValue = presentValue.add(terminalValue);

        System.out.println("Terminal Value: " + terminalValue); // Log
        System.out.println("Present Value (including Terminal Value): " + presentValue); // Log

        // Calculate Equity Value by subtracting net debt
        BigDecimal equityValue = presentValue.subtract(netDebt);

        // Calculate price per share
        BigDecimal pricePerShare = equityValue.divide(new BigDecimal(numberOfShares), 10, RoundingMode.HALF_UP);

        return pricePerShare;
    }
}