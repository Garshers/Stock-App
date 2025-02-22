package com.stockapp.StockApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an annual income statement for a company.
 */
public class IncomeStatement {
    private final LocalDate fiscalDateEnding;
    private final BigDecimal grossProfit;
    private final BigDecimal totalRevenue;
    private final BigDecimal costOfRevenue;
    private final BigDecimal costofGoodsAndServicesSold;
    private final BigDecimal operatingIncome;
    private final BigDecimal sellingGeneralAndAdministrative;
    private final BigDecimal researchAndDevelopment;
    private final BigDecimal operatingExpenses;
    private final BigDecimal investmentIncomeNet;
    private final BigDecimal netInterestIncome;
    private final BigDecimal interestIncome;
    private final BigDecimal interestExpense;
    private final BigDecimal nonInterestIncome;
    private final BigDecimal otherNonOperatingIncome;
    private final BigDecimal depreciation;
    private final BigDecimal depreciationAndAmortization;
    private final BigDecimal incomeBeforeTax;
    private final BigDecimal incomeTaxExpense;
    private final BigDecimal interestAndDebtExpense;
    private final BigDecimal netIncomeFromContinuingOperations;
    private final BigDecimal comprehensiveIncomeNetOfTax;
    private final BigDecimal ebit;
    private final BigDecimal ebitda;
    private final BigDecimal netIncome;

    /**
     * Constructs a new IncomeStatement object.
     *
     * @param fiscalDateEnding The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param grossProfit      The gross profit for the fiscal year.
     * @param totalRevenue     The total revenue for the fiscal year.
     * @param operatingIncome  The operating income for the fiscal year.
     * @param netIncome        The net income for the fiscal year.
     */
    public IncomeStatement(LocalDate fiscalDateEnding, BigDecimal grossProfit,
                           BigDecimal totalRevenue, BigDecimal operatingIncome, BigDecimal netIncome,
                           BigDecimal costOfRevenue, BigDecimal costofGoodsAndServicesSold,
                           BigDecimal sellingGeneralAndAdministrative, BigDecimal researchAndDevelopment,
                           BigDecimal operatingExpenses, BigDecimal investmentIncomeNet, BigDecimal netInterestIncome,
                           BigDecimal interestIncome, BigDecimal interestExpense, BigDecimal nonInterestIncome,
                           BigDecimal otherNonOperatingIncome, BigDecimal depreciation, BigDecimal depreciationAndAmortization,
                           BigDecimal incomeBeforeTax, BigDecimal incomeTaxExpense, BigDecimal interestAndDebtExpense,
                           BigDecimal netIncomeFromContinuingOperations, BigDecimal comprehensiveIncomeNetOfTax,
                           BigDecimal ebit, BigDecimal ebitda) {

        this.fiscalDateEnding = fiscalDateEnding;
        this.grossProfit = grossProfit;
        this.totalRevenue = totalRevenue;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
        this.costOfRevenue = costOfRevenue;
        this.costofGoodsAndServicesSold = costofGoodsAndServicesSold;
        this.sellingGeneralAndAdministrative = sellingGeneralAndAdministrative;
        this.researchAndDevelopment = researchAndDevelopment;
        this.operatingExpenses = operatingExpenses;
        this.investmentIncomeNet = investmentIncomeNet;
        this.netInterestIncome = netInterestIncome;
        this.interestIncome = interestIncome;
        this.interestExpense = interestExpense;
        this.nonInterestIncome = nonInterestIncome;
        this.otherNonOperatingIncome = otherNonOperatingIncome;
        this.depreciation = depreciation;
        this.depreciationAndAmortization = depreciationAndAmortization;
        this.incomeBeforeTax = incomeBeforeTax;
        this.incomeTaxExpense = incomeTaxExpense;
        this.interestAndDebtExpense = interestAndDebtExpense;
        this.netIncomeFromContinuingOperations = netIncomeFromContinuingOperations;
        this.comprehensiveIncomeNetOfTax = comprehensiveIncomeNetOfTax;
        this.ebit = ebit;
        this.ebitda = ebitda;
    }


    public LocalDate getFiscalDateEnding() { return fiscalDateEnding; }
    public BigDecimal getGrossProfit() { return grossProfit; }
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public BigDecimal getCostOfRevenue() { return costOfRevenue; }
    public BigDecimal getCostofGoodsAndServicesSold() { return costofGoodsAndServicesSold; }
    public BigDecimal getOperatingIncome() { return operatingIncome; }
    public BigDecimal getSellingGeneralAndAdministrative() { return sellingGeneralAndAdministrative; }
    public BigDecimal getResearchAndDevelopment() { return researchAndDevelopment; }
    public BigDecimal getOperatingExpenses() { return operatingExpenses; }
    public BigDecimal getInvestmentIncomeNet() { return investmentIncomeNet; }
    public BigDecimal getNetInterestIncome() { return netInterestIncome; }
    public BigDecimal getInterestIncome() { return interestIncome; }
    public BigDecimal getInterestExpense() { return interestExpense; }
    public BigDecimal getNonInterestIncome() { return nonInterestIncome; }
    public BigDecimal getOtherNonOperatingIncome() { return otherNonOperatingIncome; }
    public BigDecimal getDepreciation() { return depreciation; }
    public BigDecimal getDepreciationAndAmortization() { return depreciationAndAmortization; }
    public BigDecimal getIncomeBeforeTax() { return incomeBeforeTax; }
    public BigDecimal getIncomeTaxExpense() { return incomeTaxExpense; }
    public BigDecimal getInterestAndDebtExpense() { return interestAndDebtExpense; }
    public BigDecimal getNetIncomeFromContinuingOperations() { return netIncomeFromContinuingOperations; }
    public BigDecimal getComprehensiveIncomeNetOfTax() { return comprehensiveIncomeNetOfTax; }
    public BigDecimal getEbit() { return ebit; }
    public BigDecimal getEbitda() { return ebitda; }
    public BigDecimal getNetIncome() { return netIncome; }
    
    /**
     * Returns a string representation of the FinancialStatement object.
     *
     * @return A string representation of the FinancialStatement object.
     */
    @Override
    public String toString() {
        return "Income Statement{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", grossProfit=" + grossProfit +
                ", totalRevenue=" + totalRevenue +
                ", operatingIncome=" + operatingIncome +
                ", netIncome=" + netIncome +
                ", costOfRevenue=" + costOfRevenue +
                ", costofGoodsAndServicesSold=" + costofGoodsAndServicesSold +
                ", sellingGeneralAndAdministrative=" + sellingGeneralAndAdministrative +
                ", researchAndDevelopment=" + researchAndDevelopment +
                ", operatingExpenses=" + operatingExpenses +
                ", investmentIncomeNet='" + investmentIncomeNet + '\'' +
                ", netInterestIncome=" + netInterestIncome +
                ", interestIncome=" + interestIncome +
                ", interestExpense=" + interestExpense +
                ", nonInterestIncome=" + nonInterestIncome +
                ", otherNonOperatingIncome=" + otherNonOperatingIncome +
                ", depreciation=" + depreciation +
                ", depreciationAndAmortization=" + depreciationAndAmortization +
                ", incomeBeforeTax=" + incomeBeforeTax +
                ", incomeTaxExpense=" + incomeTaxExpense +
                ", interestAndDebtExpense=" + interestAndDebtExpense +
                ", netIncomeFromContinuingOperations=" + netIncomeFromContinuingOperations +
                ", comprehensiveIncomeNetOfTax=" + comprehensiveIncomeNetOfTax +
                ", ebit=" + ebit +
                ", ebitda=" + ebitda +
                '}' + "\n";
    }
}
