package com.stockapp.StockApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a balance sheet for a company.  This class stores the various
 * financial figures that make up a balance sheet.  Monetary values are stored
 * as BigDecimals, and the fiscal date is stored as a LocalDate.
 */
public class CashFlow {
    private final LocalDate fiscalDateEnding;
    private final String reportedCurrency;
    private final BigDecimal operatingCashflow;
    private final BigDecimal paymentsForOperatingActivities;
    private final BigDecimal proceedsFromOperatingActivities;
    private final BigDecimal changeInOperatingLiabilities;
    private final BigDecimal changeInOperatingAssets;
    private final BigDecimal depreciationDepletionAndAmortization;
    private final BigDecimal capitalExpenditures;
    private final BigDecimal changeInReceivables;
    private final BigDecimal changeInInventory;
    private final BigDecimal profitLoss;
    private final BigDecimal cashflowFromInvestment;
    private final BigDecimal cashflowFromFinancing;
    private final BigDecimal proceedsFromRepaymentsOfShortTermDebt;
    private final BigDecimal paymentsForRepurchaseOfCommonStock;
    private final BigDecimal paymentsForRepurchaseOfEquity;
    private final BigDecimal paymentsForRepurchaseOfPreferredStock;
    private final BigDecimal dividendPayout;
    private final BigDecimal dividendPayoutCommonStock;
    private final BigDecimal dividendPayoutPreferredStock;
    private final BigDecimal proceedsFromIssuanceOfCommonStock;
    private final BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    private final BigDecimal proceedsFromIssuanceOfPreferredStock;
    private final BigDecimal proceedsFromRepurchaseOfEquity;
    private final BigDecimal proceedsFromSaleOfTreasuryStock;
    private final BigDecimal changeInCashAndCashEquivalents;
    private final BigDecimal changeInExchangeRate;
    private final BigDecimal netIncome;

    /**
     * Constructs a new BalanceSheet object.
     *
     * @param fiscalDateEnding                                          The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param reportedCurrency                                          The currency in which the report is denominated (e.g., USD, EUR).
     * @param operatingCashflow                                         Cash flow from operating activities.
     * @param paymentsForOperatingActivities                            Payments made for operating activities.
     * @param proceedsFromOperatingActivities                           Proceeds received from operating activities.
     * @param changeInOperatingLiabilities                              Change in operating liabilities.
     * @param changeInOperatingAssets                                   Change in operating assets.
     * @param depreciationDepletionAndAmortization                      Depreciation, depletion, and amortization expense.
     * @param capitalExpenditures                                       Capital expenditures.
     * @param changeInReceivables                                       Change in receivables.
     * @param changeInInventory                                         Change in inventory.
     * @param profitLoss                                                Profit or loss for the period.
     * @param cashflowFromInvestment                                    Cash flow from investing activities.
     * @param cashflowFromFinancing                                     Cash flow from financing activities.
     * @param proceedsFromRepaymentsOfShortTermDebt                     Proceeds from repayments of short-term debt.
     * @param paymentsForRepurchaseOfCommonStock                        Payments for repurchase of common stock.
     * @param paymentsForRepurchaseOfEquity                             Payments for repurchase of equity.
     * @param paymentsForRepurchaseOfPreferredStock                     Payments for repurchase of preferred stock.
     * @param dividendPayout                                            Dividend payout.
     * @param dividendPayoutCommonStock                                 Dividend payout for common stock.
     * @param dividendPayoutPreferredStock                              Dividend payout for preferred stock.
     * @param proceedsFromIssuanceOfCommonStock                         Proceeds from issuance of common stock.
     * @param proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet Proceeds from issuance of long-term debt and capital securities, net.
     * @param proceedsFromIssuanceOfPreferredStock                      Proceeds from issuance of preferred stock.
     * @param proceedsFromRepurchaseOfEquity                            Proceeds from repurchase of equity.
     * @param proceedsFromSaleOfTreasuryStock                           Proceeds from sale of treasury stock.
     * @param changeInCashAndCashEquivalents                            Change in cash and cash equivalents.
     * @param changeInExchangeRate                                      Change in exchange rate.
     * @param netIncome                                                 Net income for the period.
     */
    public CashFlow(String fiscalDateEnding, String reportedCurrency, String operatingCashflow,
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        this.fiscalDateEnding = LocalDate.parse(fiscalDateEnding, formatter);
        this.reportedCurrency = reportedCurrency;
        this.operatingCashflow = parseBigDecimal(operatingCashflow);
        this.paymentsForOperatingActivities = parseBigDecimal(paymentsForOperatingActivities);
        this.proceedsFromOperatingActivities = parseBigDecimal(proceedsFromOperatingActivities);
        this.changeInOperatingLiabilities = parseBigDecimal(changeInOperatingLiabilities);
        this.changeInOperatingAssets = parseBigDecimal(changeInOperatingAssets);
        this.depreciationDepletionAndAmortization = parseBigDecimal(depreciationDepletionAndAmortization);
        this.capitalExpenditures = parseBigDecimal(capitalExpenditures);
        this.changeInReceivables = parseBigDecimal(changeInReceivables);
        this.changeInInventory = parseBigDecimal(changeInInventory);
        this.profitLoss = parseBigDecimal(profitLoss);
        this.cashflowFromInvestment = parseBigDecimal(cashflowFromInvestment);
        this.cashflowFromFinancing = parseBigDecimal(cashflowFromFinancing);
        this.proceedsFromRepaymentsOfShortTermDebt = parseBigDecimal(proceedsFromRepaymentsOfShortTermDebt);
        this.paymentsForRepurchaseOfCommonStock = parseBigDecimal(paymentsForRepurchaseOfCommonStock);
        this.paymentsForRepurchaseOfEquity = parseBigDecimal(paymentsForRepurchaseOfEquity);
        this.paymentsForRepurchaseOfPreferredStock = parseBigDecimal(paymentsForRepurchaseOfPreferredStock);
        this.dividendPayout = parseBigDecimal(dividendPayout);
        this.dividendPayoutCommonStock = parseBigDecimal(dividendPayoutCommonStock);
        this.dividendPayoutPreferredStock = parseBigDecimal(dividendPayoutPreferredStock);
        this.proceedsFromIssuanceOfCommonStock = parseBigDecimal(proceedsFromIssuanceOfCommonStock);
        this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = parseBigDecimal(proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet);
        this.proceedsFromIssuanceOfPreferredStock = parseBigDecimal(proceedsFromIssuanceOfPreferredStock);
        this.proceedsFromRepurchaseOfEquity = parseBigDecimal(proceedsFromRepurchaseOfEquity);
        this.proceedsFromSaleOfTreasuryStock = parseBigDecimal(proceedsFromSaleOfTreasuryStock);
        this.changeInCashAndCashEquivalents = parseBigDecimal(changeInCashAndCashEquivalents);
        this.changeInExchangeRate = parseBigDecimal(changeInExchangeRate);
        this.netIncome = parseBigDecimal(netIncome);
    }

    public CashFlow(BigDecimal capitalExpenditures, BigDecimal cashflowFromFinancing, BigDecimal cashflowFromInvestment, BigDecimal changeInCashAndCashEquivalents, BigDecimal changeInExchangeRate, BigDecimal changeInInventory, BigDecimal changeInOperatingAssets, BigDecimal changeInOperatingLiabilities, BigDecimal changeInReceivables, BigDecimal depreciationDepletionAndAmortization, BigDecimal dividendPayout, BigDecimal dividendPayoutCommonStock, BigDecimal dividendPayoutPreferredStock, LocalDate fiscalDateEnding, BigDecimal netIncome, BigDecimal operatingCashflow, BigDecimal paymentsForOperatingActivities, BigDecimal paymentsForRepurchaseOfCommonStock, BigDecimal paymentsForRepurchaseOfEquity, BigDecimal paymentsForRepurchaseOfPreferredStock, BigDecimal proceedsFromIssuanceOfCommonStock, BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet, BigDecimal proceedsFromIssuanceOfPreferredStock, BigDecimal proceedsFromOperatingActivities, BigDecimal proceedsFromRepaymentsOfShortTermDebt, BigDecimal proceedsFromRepurchaseOfEquity, BigDecimal proceedsFromSaleOfTreasuryStock, BigDecimal profitLoss, String reportedCurrency) {
        this.capitalExpenditures = capitalExpenditures;
        this.cashflowFromFinancing = cashflowFromFinancing;
        this.cashflowFromInvestment = cashflowFromInvestment;
        this.changeInCashAndCashEquivalents = changeInCashAndCashEquivalents;
        this.changeInExchangeRate = changeInExchangeRate;
        this.changeInInventory = changeInInventory;
        this.changeInOperatingAssets = changeInOperatingAssets;
        this.changeInOperatingLiabilities = changeInOperatingLiabilities;
        this.changeInReceivables = changeInReceivables;
        this.depreciationDepletionAndAmortization = depreciationDepletionAndAmortization;
        this.dividendPayout = dividendPayout;
        this.dividendPayoutCommonStock = dividendPayoutCommonStock;
        this.dividendPayoutPreferredStock = dividendPayoutPreferredStock;
        this.fiscalDateEnding = fiscalDateEnding;
        this.netIncome = netIncome;
        this.operatingCashflow = operatingCashflow;
        this.paymentsForOperatingActivities = paymentsForOperatingActivities;
        this.paymentsForRepurchaseOfCommonStock = paymentsForRepurchaseOfCommonStock;
        this.paymentsForRepurchaseOfEquity = paymentsForRepurchaseOfEquity;
        this.paymentsForRepurchaseOfPreferredStock = paymentsForRepurchaseOfPreferredStock;
        this.proceedsFromIssuanceOfCommonStock = proceedsFromIssuanceOfCommonStock;
        this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
        this.proceedsFromIssuanceOfPreferredStock = proceedsFromIssuanceOfPreferredStock;
        this.proceedsFromOperatingActivities = proceedsFromOperatingActivities;
        this.proceedsFromRepaymentsOfShortTermDebt = proceedsFromRepaymentsOfShortTermDebt;
        this.proceedsFromRepurchaseOfEquity = proceedsFromRepurchaseOfEquity;
        this.proceedsFromSaleOfTreasuryStock = proceedsFromSaleOfTreasuryStock;
        this.profitLoss = profitLoss;
        this.reportedCurrency = reportedCurrency;
    }

    /**
     * Parses a string value into a BigDecimal.  Handles null and "None" values.
     * If a NumberFormatException occurs during parsing, an error message is printed
     * to System.err and null is returned.
     *
     * @param value The string value to parse.
     * @return The BigDecimal representation of the string, or null if the string
     *         is null, "None", or cannot be parsed as a BigDecimal.
     */
    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.equals("None")) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing BigDecimal value: " + value + ". Returning null.");
            return null;
        }
    }

    public LocalDate getFiscalDateEnding() { return fiscalDateEnding; }
    public String getReportedCurrency() { return reportedCurrency; }
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
