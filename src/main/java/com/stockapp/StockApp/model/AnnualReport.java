package com.stockapp.StockApp.model;

public class AnnualReport {
    private String fiscalDateEnding;
    private String reportedCurrency;
    private String grossProfit;
    private String totalRevenue;
    private String operatingIncome;
    private String netIncome;

    public AnnualReport(String fiscalDateEnding, String reportedCurrency, String grossProfit,
                        String totalRevenue, String operatingIncome, String netIncome) {
        this.fiscalDateEnding = fiscalDateEnding;
        this.reportedCurrency = reportedCurrency;
        this.grossProfit = grossProfit;
        this.totalRevenue = totalRevenue;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", grossProfit='" + grossProfit + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                ", operatingIncome='" + operatingIncome + '\'' +
                ", netIncome='" + netIncome + '\'' +
                '}';
    }
}
