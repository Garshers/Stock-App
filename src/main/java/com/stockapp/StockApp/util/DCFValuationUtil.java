package com.stockapp.StockApp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Utility class for Discounted Cash Flow (DCF) valuation calculations.
 */
public class DCFValuationUtil {
    
    /**
     * Calculates the price per share using the Discounted Cash Flow (DCF) method.
     * This method projects future free cash flows based on provided growth rates, 
     * calculates their present value, and adds a terminal value using the Gordon Growth Model.
     * Finally, it derives the price per share by subtracting net debt and dividing by the number of outstanding shares.
     *
     * @param lastYearFCF     The free cash flow from the last year.
     * @param growthRates     A list of annual growth rates for free cash flow, including the terminal growth rate as the last element.
     * @param discountRate    The discount rate used to calculate the present value of future cash flows.
     * @param numberOfShares  The number of outstanding shares.
     * @param netDebt         The net debt of the company (total debt minus cash and cash equivalents).
     * @return The calculated price per share.
     * @throws ArithmeticException      If the discount rate is less than or equal to the terminal growth rate.
     * @throws IllegalArgumentException If the discount rate is not positive or lastYearFCF is negative.
     */
    public BigDecimal calculateDCF(
            BigDecimal lastYearFCF, List<BigDecimal> growthRates, 
            BigDecimal discountRate, BigDecimal numberOfShares,
            BigDecimal netDebt)
        {

        int n = growthRates.size() - 1;
        BigDecimal terminalGrowthRate = growthRates.get(n);

        if (discountRate.compareTo(terminalGrowthRate) <= 0) {
            throw new ArithmeticException("Discount rate must be greater than terminal growth rate.");
        }
        if (discountRate.compareTo(BigDecimal.ZERO) <= 0 || lastYearFCF.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid input values: discount rate must be positive, and lastYearFCF cannot be negative.");
        }

        BigDecimal currentFCF = lastYearFCF;
        BigDecimal presentValue = BigDecimal.ZERO;

        // Calculate the present value of projected free cash flows
        for (int i = 0; i < n; i++) {
            currentFCF = currentFCF.multiply(BigDecimal.ONE.add(growthRates.get(i)));
            BigDecimal discountFactor = BigDecimal.ONE.add(discountRate).pow(i+1);
            presentValue = presentValue.add(currentFCF.divide(discountFactor, 10, RoundingMode.HALF_UP));
        }

        // Calculate the terminal value using the Gordon Growth Model
        BigDecimal terminalValue = currentFCF.multiply(BigDecimal.ONE.add(terminalGrowthRate))
                .divide(discountRate.subtract(terminalGrowthRate), 10, RoundingMode.HALF_UP);

        BigDecimal terminalValueDiscountFactor = BigDecimal.ONE.add(discountRate).pow(n);
        terminalValue = terminalValue.divide(terminalValueDiscountFactor, 10, RoundingMode.HALF_UP);
        
        // Total present value including terminal value
        presentValue = presentValue.add(terminalValue);

        // Calculate equity value by subtracting net debt
        BigDecimal equityValue = presentValue.subtract(netDebt);

        // Calculate price per share
        return equityValue.divide(numberOfShares, 2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the Weighted Average Cost of Capital (WACC) using financial statement and market data.
     *
     * @param riskFreeRate          Risk-free rate of return (Rf) (e.g., 10-year Treasury yield). From market data.
     * @param beta                  Beta of the company (β). From financial data provider.
     * @param interestExpense       Interest expense (I). From the income statement.
     * @param totalDebt             Total debt (D). From the balance sheet.
     * @param marketCapitalization  Market capitalization (E). From current market data.
     * @param taxProvision          Tax Provision (or Income Tax Expense) (ITE). From the income statement.
     * @param pretaxIncome          Pretax Income (or Income Before Taxes) (IBT). From the income statement.
     * @param marketRiskPremium     Market risk premium (Rm - Rf). From market data.
     * @return The calculated WACC.
     * @throws IllegalArgumentException If any of the input parameters are invalid.
     */
    public BigDecimal calculateWACCFromFinancialAndMarketData(
            BigDecimal riskFreeRate, BigDecimal beta, 
            BigDecimal interestExpense, BigDecimal totalDebt, 
            BigDecimal marketCapitalization, BigDecimal taxProvision,
            BigDecimal pretaxIncome, BigDecimal marketRiskPremium) 
        {
        // Calculate Cost of Equity:
        // Re = Rf + (β * (Rm - Rf))
        BigDecimal costOfEquity = riskFreeRate.add(beta.multiply(marketRiskPremium));

        // Calculate Cost of Debt:
        // Rd = I / D
        if (totalDebt.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Total debt cannot be zero.");
        }
        BigDecimal costOfDebt = interestExpense.divide(totalDebt, 10, RoundingMode.HALF_UP);

        // Calculate Tax Rate:
        // Tc = ITE / IBT
        BigDecimal taxRate;
        if (marketCapitalization.compareTo(BigDecimal.ZERO) == 0) {
            taxRate = BigDecimal.ZERO; // Handle the case where market capitalization is zero
        } else {
            taxRate = taxProvision.divide(marketCapitalization, 10, RoundingMode.HALF_UP); // Correct calculation
        }

        // Equity and Debt Values:
        BigDecimal equityValue = marketCapitalization;
        BigDecimal debtValue = totalDebt;

        return calculateWACC(costOfEquity, costOfDebt, equityValue, debtValue, taxRate);
    }

    /**
     * Calculates the Weighted Average Cost of Capital (WACC) using financial statement and market data.
     * This method calculates the WACC based on the following formula:
     * 
     * WACC = (E / (V)) * Re + (D / V)* Rd * (1 – Tc)
     * Where: V = E + D
     *
     * @param costOfEquity Cost of equity. [Re]
     * @param costOfDebt   Cost of debt. [Rd]
     * @param equityValue  Equity value. [E]
     * @param debtValue    Debt value. [D]
     * @param taxRate      Tax rate. [Tc]
     * @return The calculated WACC.
     * @throws IllegalArgumentException If equity or debt values are not positive.
     */
    private BigDecimal calculateWACC(BigDecimal costOfEquity, BigDecimal costOfDebt, BigDecimal equityValue, BigDecimal debtValue, BigDecimal taxRate) {
        if (equityValue.compareTo(BigDecimal.ZERO) <= 0 || debtValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Equity and Debt values must be positive.");
        }

        BigDecimal totalValue = equityValue.add(debtValue); // [V = E + D]

        BigDecimal equityWeight = equityValue.divide(totalValue, 10, RoundingMode.HALF_UP); // [E / V]
        BigDecimal debtWeight = debtValue.divide(totalValue, 10, RoundingMode.HALF_UP); // [D / V]

        BigDecimal afterTaxCostOfDebt = costOfDebt.multiply(BigDecimal.ONE.subtract(taxRate)); // [Rd * (1 - Tc)]

        BigDecimal wacc = (equityWeight.multiply(costOfEquity)).add(debtWeight.multiply(afterTaxCostOfDebt));
                            // [(E / V) * Re]                  +    [(D / V) * (Rd * (1 - Tc))]

        return wacc.setScale(4, RoundingMode.HALF_UP);
    }
}