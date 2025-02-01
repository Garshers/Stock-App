package com.stockapp.StockApp.model;

public class AnnualReport {
    final private String fiscalDateEnding;
    final private String reportedCurrency;
    final private Long grossProfit;
    final private Long totalRevenue;
    final private Long operatingIncome;
    final private Long netIncome;

    public AnnualReport(String fiscalDateEnding, String reportedCurrency, Long grossProfit,
                        Long totalRevenue, Long operatingIncome, Long netIncome){
                            
        this.fiscalDateEnding = fiscalDateEnding;
        this.grossProfit = grossProfit;
        this.totalRevenue = totalRevenue;
        this.operatingIncome = operatingIncome;
        this.netIncome = netIncome;
        this.reportedCurrency = reportedCurrency;
    }

    public String getFiscalDateEnding(){
        return fiscalDateEnding;
    }

    public String getReportedCurrency(){
        return reportedCurrency;
    }

    public Long getGrossProfit(){
        return grossProfit;
    }

    public Long getTotalRevenue(){
        return totalRevenue;
    }

    public Long getOperatingIncome(){
        return operatingIncome;
    }

    public Long getNetIncome(){
        return netIncome;
    }
    
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
