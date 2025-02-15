package com.stockapp.StockApp.model;

/**
 * Represents an annual income statement for a company.
 */
public class IncomeStatement {
    final private String fiscalDateEnding;
    final private Long grossProfit;
    final private Long totalRevenue;
    final private Long operatingIncome;
    final private Long netIncome;

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
                        Long totalRevenue, Long operatingIncome, Long netIncome){
                            
        this.fiscalDateEnding = fiscalDateEnding;
        this.grossProfit = grossProfit;
        this.totalRevenue = totalRevenue;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
    }

    /**
     * Returns the ending date of the fiscal year.
     *
     * @return The fiscal year ending date.
     */
    public String getFiscalDateEnding(){
        return fiscalDateEnding;
    }

    /**
     * Returns the gross profit.
     *
     * @return The gross profit.
     */
    public Long getGrossProfit(){
        return grossProfit;
    }

    /**
     * Returns the total revenue.
     *
     * @return The total revenue.
     */
    public Long getTotalRevenue(){
        return totalRevenue;
    }

    /**
     * Returns the operating income.
     *
     * @return The operating income.
     */
    public Long getOperatingIncome(){
        return operatingIncome;
    }

    /**
     * Returns the net income.
     *
     * @return The net income.
     */
    public Long getNetIncome(){
        return netIncome;
    }
    
    /**
     * Returns a string representation of the FinancialStatement object.
     *
     * @return A string representation of the FinancialStatement object.
     */
    @Override
    public String toString(){
        return "Income Statement{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", grossProfit='" + grossProfit + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                ", operatingIncome='" + operatingIncome + '\'' +
                ", netIncome='" + netIncome + '\'' +
                '}' + "\n";
    }
}
