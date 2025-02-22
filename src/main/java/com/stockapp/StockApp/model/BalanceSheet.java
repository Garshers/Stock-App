package com.stockapp.StockApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a balance sheet for a company. This class stores the various
 * financial figures that make up a balance sheet.
 */
public class BalanceSheet {
    final private LocalDate fiscalDateEnding;
    final private BigDecimal operatingCashflow;
    final private BigDecimal paymentsForOperatingActivities;
    final private BigDecimal proceedsFromOperatingActivities;
    final private BigDecimal changeInOperatingLiabilities;
    final private BigDecimal changeInOperatingAssets;
    final private BigDecimal depreciationDepletionAndAmortization;
    final private BigDecimal capitalExpenditures;
    final private BigDecimal changeInReceivables;
    final private BigDecimal changeInInventory;
    final private BigDecimal profitLoss;
    final private BigDecimal cashflowFromInvestment;
    final private BigDecimal cashflowFromFinancing;
    final private BigDecimal proceedsFromRepaymentsOfShortTermDebt;
    final private BigDecimal paymentsForRepurchaseOfCommonStock;
    final private BigDecimal paymentsForRepurchaseOfEquity;
    final private BigDecimal paymentsForRepurchaseOfPreferredStock;
    final private BigDecimal dividendPayout;
    final private BigDecimal dividendPayoutCommonStock;
    final private BigDecimal dividendPayoutPreferredStock;
    final private BigDecimal proceedsFromIssuanceOfCommonStock;
    final private BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    final private BigDecimal proceedsFromIssuanceOfPreferredStock;
    final private BigDecimal proceedsFromRepurchaseOfEquity;
    final private BigDecimal proceedsFromSaleOfTreasuryStock;
    final private BigDecimal changeInCashAndCashEquivalents;
    final private BigDecimal changeInExchangeRate;
    final private BigDecimal netIncome;


    /**
     * Constructs a new BalanceSheet object.
     *
     * @param fiscalDateEnding The ending date of the fiscal year (e.g., YYYY-MM-DD).
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
    public BalanceSheet(LocalDate fiscalDateEnding, BigDecimal operatingCashflow,
                       BigDecimal paymentsForOperatingActivities, BigDecimal proceedsFromOperatingActivities,
                       BigDecimal changeInOperatingLiabilities, BigDecimal changeInOperatingAssets,
                       BigDecimal depreciationDepletionAndAmortization, BigDecimal capitalExpenditures,
                       BigDecimal changeInReceivables, BigDecimal changeInInventory, BigDecimal profitLoss,
                       BigDecimal cashflowFromInvestment, BigDecimal cashflowFromFinancing,
                       BigDecimal proceedsFromRepaymentsOfShortTermDebt, BigDecimal paymentsForRepurchaseOfCommonStock,
                       BigDecimal paymentsForRepurchaseOfEquity, BigDecimal paymentsForRepurchaseOfPreferredStock,
                       BigDecimal dividendPayout, BigDecimal dividendPayoutCommonStock, BigDecimal dividendPayoutPreferredStock,
                       BigDecimal proceedsFromIssuanceOfCommonStock, BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet,
                       BigDecimal proceedsFromIssuanceOfPreferredStock, BigDecimal proceedsFromRepurchaseOfEquity,
                       BigDecimal proceedsFromSaleOfTreasuryStock, BigDecimal changeInCashAndCashEquivalents,
                       BigDecimal changeInExchangeRate, BigDecimal netIncome) {

        this.fiscalDateEnding = fiscalDateEnding;
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

    public LocalDate getFiscalDateEnding() { return fiscalDateEnding; }
    public BigDecimal getOperatingCashflow() { return operatingCashflow; }
    public BigDecimal getPaymentsForOperatingActivities() { return paymentsForOperatingActivities; }
    public BigDecimal getProceedsFromOperatingActivities() { return proceedsFromOperatingActivities; }
    public BigDecimal getChangeInOperatingLiabilities() { return changeInOperatingLiabilities; }
    public BigDecimal getChangeInOperatingAssets() { return changeInOperatingAssets; }
    public BigDecimal getDepreciationDepletionAndAmortization() { return depreciationDepletionAndAmortization; }
    public BigDecimal getCapitalExpenditures() { return capitalExpenditures; }
    public BigDecimal getChangeInReceivables() { return changeInReceivables; }
    public BigDecimal getChangeInInventory() { return changeInInventory; }
    public BigDecimal getProfitLoss() { return profitLoss; }
    public BigDecimal getCashflowFromInvestment() { return cashflowFromInvestment; }
    public BigDecimal getCashflowFromFinancing() { return cashflowFromFinancing; }
    public BigDecimal getProceedsFromRepaymentsOfShortTermDebt() { return proceedsFromRepaymentsOfShortTermDebt; }
    public BigDecimal getPaymentsForRepurchaseOfCommonStock() { return paymentsForRepurchaseOfCommonStock; }
    public BigDecimal getPaymentsForRepurchaseOfEquity() { return paymentsForRepurchaseOfEquity; }
    public BigDecimal getPaymentsForRepurchaseOfPreferredStock() { return paymentsForRepurchaseOfPreferredStock; }
    public BigDecimal getDividendPayout() { return dividendPayout; }
    public BigDecimal getDividendPayoutCommonStock() { return dividendPayoutCommonStock; }
    public BigDecimal getDividendPayoutPreferredStock() { return dividendPayoutPreferredStock; }
    public BigDecimal getProceedsFromIssuanceOfCommonStock() { return proceedsFromIssuanceOfCommonStock; }
    public BigDecimal getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() { return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet; }
    public BigDecimal getProceedsFromIssuanceOfPreferredStock() { return proceedsFromIssuanceOfPreferredStock; }
    public BigDecimal getProceedsFromRepurchaseOfEquity() { return proceedsFromRepurchaseOfEquity; }
    public BigDecimal getProceedsFromSaleOfTreasuryStock() { return proceedsFromSaleOfTreasuryStock; }
    public BigDecimal getChangeInCashAndCashEquivalents() { return changeInCashAndCashEquivalents; }
    public BigDecimal getChangeInExchangeRate() { return changeInExchangeRate; }
    public BigDecimal getNetIncome() { return netIncome; }
}
