package com.stockapp.StockApp.model;

/**
 * Represents an annual financial report for a company.
 */
public class AnnualReport {
    final private String fiscalDateEnding;
    final private String reportedCurrency;
    final private Long grossProfit;
    final private Long totalRevenue;
    final private Long operatingIncome;
    final private Long netIncome;

    /**
     * Constructs a new AnnualReport object.
     *
     * @param fiscalDateEnding The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param reportedCurrency The currency in which the report is denominated (e.g., USD, EUR).
     * @param grossProfit      The gross profit for the fiscal year.
     * @param totalRevenue     The total revenue for the fiscal year.
     * @param operatingIncome  The operating income for the fiscal year.
     * @param netIncome        The net income for the fiscal year.
     */
    public AnnualReport(String fiscalDateEnding, String reportedCurrency, Long grossProfit,
                        Long totalRevenue, Long operatingIncome, Long netIncome){
                            
        this.fiscalDateEnding = fiscalDateEnding;
        this.grossProfit = grossProfit;
        this.totalRevenue = totalRevenue;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
        this.reportedCurrency = reportedCurrency;
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
     * Returns the currency in which the report is denominated.
     *
     * @return The reported currency.
     */
    public String getReportedCurrency(){
        return reportedCurrency;
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
     * Returns a string representation of the AnnualReport object.
     *
     * @return A string representation of the AnnualReport object.
     */
    @Override
    public String toString(){
        return "AnnualReport{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", grossProfit='" + grossProfit + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                ", operatingIncome='" + operatingIncome + '\'' +
                ", netIncome='" + netIncome + '\'' +
                '}' + "\n";
    }
}
