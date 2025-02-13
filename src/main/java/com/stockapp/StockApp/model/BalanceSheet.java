package com.stockapp.StockApp.model;

/**
 * Represents a balance sheet for a company. This class stores the various
 * financial figures that make up a balance sheet.
 */
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


    /**
     * Constructs a new BalanceSheet object.
     *
     * @param fiscalDateEnding The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param reportedCurrency The currency in which the report is denominated (e.g., USD, EUR).
     * @param operatingCashflow Cash flow from operating activities.
     * @param paymentsForOperatingActivities Payments made for operating activities.
     * @param proceedsFromOperatingActivities Proceeds received from operating activities.
     * @param changeInOperatingLiabilities Change in operating liabilities.
     * @param changeInOperatingAssets Change in operating assets.
     * @param depreciationDepletionAndAmortization Depreciation, depletion, and amortization expense.
     * @param capitalExpenditures Capital expenditures.
     * @param changeInReceivables Change in receivables.
     * @param changeInInventory Change in inventory.
     * @param profitLoss Profit or loss for the period.
     * @param cashflowFromInvestment Cash flow from investing activities.
     * @param cashflowFromFinancing Cash flow from financing activities.
     * @param proceedsFromRepaymentsOfShortTermDebt Proceeds from repayments of short-term debt.
     * @param paymentsForRepurchaseOfCommonStock Payments for repurchase of common stock.
     * @param paymentsForRepurchaseOfEquity Payments for repurchase of equity.
     * @param paymentsForRepurchaseOfPreferredStock Payments for repurchase of preferred stock.
     * @param dividendPayout Dividend payout.
     * @param dividendPayoutCommonStock Dividend payout for common stock.
     * @param dividendPayoutPreferredStock Dividend payout for preferred stock.
     * @param proceedsFromIssuanceOfCommonStock Proceeds from issuance of common stock.
     * @param proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet Proceeds from issuance of long-term debt and capital securities, net.
     * @param proceedsFromIssuanceOfPreferredStock Proceeds from issuance of preferred stock.
     * @param proceedsFromRepurchaseOfEquity Proceeds from repurchase of equity.
     * @param proceedsFromSaleOfTreasuryStock Proceeds from sale of treasury stock.
     * @param changeInCashAndCashEquivalents Change in cash and cash equivalents.
     * @param changeInExchangeRate Change in exchange rate.
     * @param netIncome Net income for the period.
     */
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
