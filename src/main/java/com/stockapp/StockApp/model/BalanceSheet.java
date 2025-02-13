package com.stockapp.StockApp.model;

public class BalanceSheet {
    final private String fiscalDateEnding;
    final private String reportedCurrency;
    final private String operatingCashflow;
    final private String paymentsForOperatingActivities;
    final private String proceedsFromOperatingActivities;
    final private String changeInOperatingLiabilities;
    final private String changeInOperatingAssets;
    final private String depreciationDepletionAndAmortization;
    final private String capitalExpenditures;
    final private String changeInReceivables;
    final private String changeInInventory;
    final private String profitLoss;
    final private String cashflowFromInvestment;
    final private String cashflowFromFinancing;
    final private String proceedsFromRepaymentsOfShortTermDebt;
    final private String paymentsForRepurchaseOfCommonStock;
    final private String paymentsForRepurchaseOfEquity;
    final private String paymentsForRepurchaseOfPreferredStock;
    final private String dividendPayout;
    final private String dividendPayoutCommonStock;
    final private String dividendPayoutPreferredStock;
    final private String proceedsFromIssuanceOfCommonStock;
    final private String proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    final private String proceedsFromIssuanceOfPreferredStock;
    final private String proceedsFromRepurchaseOfEquity;
    final private String proceedsFromSaleOfTreasuryStock;
    final private String changeInCashAndCashEquivalents;
    final private String changeInExchangeRate;
    final private String netIncome;


    public BalanceSheet(String fiscalDateEnding, String reportedCurrency, String operatingCashflow,
                       String paymentsForOperatingActivities, String proceedsFromOperatingActivities,
                       String changeInOperatingLiabilities, String changeInOperatingAssets,
                       String depreciationDepletionAndAmortization, String capitalExpenditures,
                       String changeInReceivables, String changeInInventory, String profitLoss,
                       String cashflowFromInvestment, String cashflowFromFinancing,
                       String proceedsFromRepaymentsOfShortTermDebt, String paymentsForRepurchaseOfCommonStock,
                       String paymentsForRepurchaseOfEquity, String paymentsForRepurchaseOfPreferredStock,
                       String dividendPayout, String dividendPayoutCommonStock, String dividendPayoutPreferredStock,
                       String proceedsFromIssuanceOfCommonStock, String proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet,
                       String proceedsFromIssuanceOfPreferredStock, String proceedsFromRepurchaseOfEquity,
                       String proceedsFromSaleOfTreasuryStock, String changeInCashAndCashEquivalents,
                       String changeInExchangeRate, String netIncome) {
        this.fiscalDateEnding = fiscalDateEnding;
        this.reportedCurrency = reportedCurrency;
        this.operatingCashflow = operatingCashflow;
        this.paymentsForOperatingActivities = paymentsForOperatingActivities;
        this.proceedsFromOperatingActivities = proceedsFromOperatingActivities;
        this.changeInOperatingLiabilities = changeInOperatingLiabilities;
        this.changeInOperatingAssets = changeInOperatingAssets;
        this.depreciationDepletionAndAmortization = depreciationDepletionAndAmortization;
        this.capitalExpenditures = capitalExpenditures;
        this.changeInReceivables = changeInReceivables;
        this.changeInInventory = changeInInventory;
        this.profitLoss = profitLoss;
        this.cashflowFromInvestment = cashflowFromInvestment;
        this.cashflowFromFinancing = cashflowFromFinancing;
        this.proceedsFromRepaymentsOfShortTermDebt = proceedsFromRepaymentsOfShortTermDebt;
        this.paymentsForRepurchaseOfCommonStock = paymentsForRepurchaseOfCommonStock;
        this.paymentsForRepurchaseOfEquity = paymentsForRepurchaseOfEquity;
        this.paymentsForRepurchaseOfPreferredStock = paymentsForRepurchaseOfPreferredStock;
        this.dividendPayout = dividendPayout;
        this.dividendPayoutCommonStock = dividendPayoutCommonStock;
        this.dividendPayoutPreferredStock = dividendPayoutPreferredStock;
        this.proceedsFromIssuanceOfCommonStock = proceedsFromIssuanceOfCommonStock;
        this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
        this.proceedsFromIssuanceOfPreferredStock = proceedsFromIssuanceOfPreferredStock;
        this.proceedsFromRepurchaseOfEquity = proceedsFromRepurchaseOfEquity;
        this.proceedsFromSaleOfTreasuryStock = proceedsFromSaleOfTreasuryStock;
        this.changeInCashAndCashEquivalents = changeInCashAndCashEquivalents;
        this.changeInExchangeRate = changeInExchangeRate;
        this.netIncome = netIncome;
    }

    public String getFiscalDateEnding() { return fiscalDateEnding; }
    public String getReportedCurrency() { return reportedCurrency; }
    public String getOperatingCashflow() { return operatingCashflow; }
    public String getPaymentsForOperatingActivities() { return paymentsForOperatingActivities; }
    public String getProceedsFromOperatingActivities() { return proceedsFromOperatingActivities; }
    public String getChangeInOperatingLiabilities() { return changeInOperatingLiabilities; }
    public String getChangeInOperatingAssets() { return changeInOperatingAssets; }
    public String getDepreciationDepletionAndAmortization() { return depreciationDepletionAndAmortization; }
    public String getCapitalExpenditures() { return capitalExpenditures; }
    public String getChangeInReceivables() { return changeInReceivables; }
    public String getChangeInInventory() { return changeInInventory; }
    public String getProfitLoss() { return profitLoss; }
    public String getCashflowFromInvestment() { return cashflowFromInvestment; }
    public String getCashflowFromFinancing() { return cashflowFromFinancing; }
    public String getProceedsFromRepaymentsOfShortTermDebt() { return proceedsFromRepaymentsOfShortTermDebt; }
    public String getPaymentsForRepurchaseOfCommonStock() { return paymentsForRepurchaseOfCommonStock; }
    public String getPaymentsForRepurchaseOfEquity() { return paymentsForRepurchaseOfEquity; }
    public String getPaymentsForRepurchaseOfPreferredStock() { return paymentsForRepurchaseOfPreferredStock; }
    public String getDividendPayout() { return dividendPayout; }
    public String getDividendPayoutCommonStock() { return dividendPayoutCommonStock; }
    public String getDividendPayoutPreferredStock() { return dividendPayoutPreferredStock; }
    public String getProceedsFromIssuanceOfCommonStock() { return proceedsFromIssuanceOfCommonStock; }
    public String getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() { return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet; }
    public String getProceedsFromIssuanceOfPreferredStock() { return proceedsFromIssuanceOfPreferredStock; }
    public String getProceedsFromRepurchaseOfEquity() { return proceedsFromRepurchaseOfEquity; }
    public String getProceedsFromSaleOfTreasuryStock() { return proceedsFromSaleOfTreasuryStock; }
    public String getChangeInCashAndCashEquivalents() { return changeInCashAndCashEquivalents; }
    public String getChangeInExchangeRate() { return changeInExchangeRate; }
    public String getNetIncome() { return netIncome; }
}
