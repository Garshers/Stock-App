package com.stockapp.StockApp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an annual income statement for a company.
 */
public class IncomeStatement {
    private final LocalDate fiscalDateEnding;
    private final Long grossProfit;
    private final Long totalRevenue;
    private final Long costOfRevenue;
    private final Long costofGoodsAndServicesSold;
    private final Long operatingIncome;
    private final Long sellingGeneralAndAdministrative;
    private final Long researchAndDevelopment;
    private final Long operatingExpenses;
    private final Long investmentIncomeNet;
    private final Long netInterestIncome;
    private final Long interestIncome;
    private final Long interestExpense;
    private final Long nonInterestIncome;
    private final Long otherNonOperatingIncome;
    private final Long depreciation;
    private final Long depreciationAndAmortization;
    private final Long incomeBeforeTax;
    private final Long incomeTaxExpense;
    private final Long interestAndDebtExpense;
    private final Long netIncomeFromContinuingOperations;
    private final Long comprehensiveIncomeNetOfTax;
    private final Long ebit;
    private final Long ebitda;
    private final Long netIncome;

    /**
     * Constructs a new IncomeStatement object.
     *
     * @param fiscalDateEnding The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param grossProfit      The gross profit for the fiscal year.
     * @param totalRevenue     The total revenue for the fiscal year.
     * @param operatingIncome  The operating income for the fiscal year.
     * @param netIncome        The net income for the fiscal year.
     */
    public IncomeStatement(String fiscalDateEnding, Long grossProfit,
                           Long totalRevenue, Long operatingIncome, Long netIncome,
                           Long costOfRevenue, Long costofGoodsAndServicesSold,
                           Long sellingGeneralAndAdministrative, Long researchAndDevelopment,
                           Long operatingExpenses, Long investmentIncomeNet, Long netInterestIncome,
                           Long interestIncome, Long interestExpense, Long nonInterestIncome,
                           Long otherNonOperatingIncome, Long depreciation, Long depreciationAndAmortization,
                           Long incomeBeforeTax, Long incomeTaxExpense, Long interestAndDebtExpense,
                           Long netIncomeFromContinuingOperations, Long comprehensiveIncomeNetOfTax,
                           Long ebit, Long ebitda) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fiscalDateEnding = LocalDate.parse(fiscalDateEnding, formatter);
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
    public Long getGrossProfit() { return grossProfit; }
    public Long getTotalRevenue() { return totalRevenue; }
    public Long getCostOfRevenue() { return costOfRevenue; }
    public Long getCostofGoodsAndServicesSold() { return costofGoodsAndServicesSold; }
    public Long getOperatingIncome() { return operatingIncome; }
    public Long getSellingGeneralAndAdministrative() { return sellingGeneralAndAdministrative; }
    public Long getResearchAndDevelopment() { return researchAndDevelopment; }
    public Long getOperatingExpenses() { return operatingExpenses; }
    public Long getInvestmentIncomeNet() { return investmentIncomeNet; }
    public Long getNetInterestIncome() { return netInterestIncome; }
    public Long getInterestIncome() { return interestIncome; }
    public Long getInterestExpense() { return interestExpense; }
    public Long getNonInterestIncome() { return nonInterestIncome; }
    public Long getOtherNonOperatingIncome() { return otherNonOperatingIncome; }
    public Long getDepreciation() { return depreciation; }
    public Long getDepreciationAndAmortization() { return depreciationAndAmortization; }
    public Long getIncomeBeforeTax() { return incomeBeforeTax; }
    public Long getIncomeTaxExpense() { return incomeTaxExpense; }
    public Long getInterestAndDebtExpense() { return interestAndDebtExpense; }
    public Long getNetIncomeFromContinuingOperations() { return netIncomeFromContinuingOperations; }
    public Long getComprehensiveIncomeNetOfTax() { return comprehensiveIncomeNetOfTax; }
    public Long getEbit() { return ebit; }
    public Long getEbitda() { return ebitda; }
    public Long getNetIncome() { return netIncome; }
    
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
