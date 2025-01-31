package com.stockapp.StockApp.model;

public class AnnualReport {
    private String fiscalDateEnding;
    private String reportedCurrency;
    private Long grossProfit;
    private Long totalRevenue;
    private Long operatingIncome;
    private Long netIncome;

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
