package com.stockapp.StockApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a balance sheet for a company. This class stores the various
 * financial figures that make up a balance sheet.
 */
public class BalanceSheet {
    final private LocalDate fiscalDateEnding;
    final private BigDecimal totalAssets;
    final private BigDecimal totalCurrentAssets;
    final private BigDecimal cashAndCashEquivalentsAtCarryingValue;
    final private BigDecimal cashAndShortTermInvestments;
    final private BigDecimal inventory;
    final private BigDecimal currentNetReceivables;
    final private BigDecimal totalNonCurrentAssets;
    final private BigDecimal propertyPlantEquipment;
    final private BigDecimal accumulatedDepreciationAmortizationPPE;
    final private BigDecimal intangibleAssets;
    final private BigDecimal intangibleAssetsExcludingGoodwill;
    final private BigDecimal goodwill;
    final private BigDecimal investments;
    final private BigDecimal longTermInvestments;
    final private BigDecimal shortTermInvestments;
    final private BigDecimal otherCurrentAssets;
    final private BigDecimal otherNonCurrentAssets;
    final private BigDecimal totalLiabilities;
    final private BigDecimal totalCurrentLiabilities;
    final private BigDecimal currentAccountsPayable;
    final private BigDecimal deferredRevenue;
    final private BigDecimal currentDebt;
    final private BigDecimal shortTermDebt;
    final private BigDecimal totalNonCurrentLiabilities;
    final private BigDecimal capitalLeaseObligations;
    final private BigDecimal longTermDebt;
    final private BigDecimal currentLongTermDebt;
    final private BigDecimal longTermDebtNoncurrent;
    final private BigDecimal shortLongTermDebtTotal;
    final private BigDecimal otherCurrentLiabilities;
    final private BigDecimal otherNonCurrentLiabilities;
    final private BigDecimal totalShareholderEquity;
    final private BigDecimal treasuryStock;
    final private BigDecimal retainedEarnings;
    final private BigDecimal commonStock;
    final private BigDecimal commonStockSharesOutstanding;


    /**
     * Constructs a new BalanceSheet object.
     *
     * @param fiscalDateEnding                       The ending date of the fiscal year (e.g., YYYY-MM-DD).
     * @param totalAssets                            Total assets of the company.
     * @param totalCurrentAssets                     Total current assets of the company.
     * @param cashAndCashEquivalentsAtCarryingValue  Cash and cash equivalents at carrying value.
     * @param cashAndShortTermInvestments            Cash and short-term investments.
     * @param inventory                              Inventory value.
     * @param currentNetReceivables                  Current net receivables.
     * @param totalNonCurrentAssets                  Total non-current assets.
     * @param propertyPlantEquipment                 Property, plant, and equipment.
     * @param accumulatedDepreciationAmortizationPPE Accumulated depreciation and amortization of PPE.
     * @param intangibleAssets                       Intangible assets.
     * @param intangibleAssetsExcludingGoodwill      Intangible assets excluding goodwill.
     * @param goodwill                               Goodwill.
     * @param investments                            Investments.
     * @param longTermInvestments                    Long-term investments.
     * @param shortTermInvestments                   Short-term investments.
     * @param otherCurrentAssets                     Other current assets.
     * @param otherNonCurrentAssets                  Other non-current assets.
     * @param totalLiabilities                       Total liabilities.
     * @param totalCurrentLiabilities                Total current liabilities.
     * @param currentAccountsPayable                 Current accounts payable.
     * @param deferredRevenue                        Deferred revenue.
     * @param currentDebt                            Current debt.
     * @param shortTermDebt                          Short-term debt.
     * @param totalNonCurrentLiabilities             Total non-current liabilities.
     * @param capitalLeaseObligations                Capital lease obligations.
     * @param longTermDebt                           Long-term debt.
     * @param currentLongTermDebt                    Current long-term debt.
     * @param longTermDebtNoncurrent                 Long-term debt (non-current).
     * @param shortLongTermDebtTotal                 Total short and long-term debt.
     * @param otherCurrentLiabilities                Other current liabilities.
     * @param otherNonCurrentLiabilities             Other non-current liabilities.
     * @param totalShareholderEquity                 Total shareholder equity.
     * @param treasuryStock                          Treasury stock.
     * @param retainedEarnings                       Retained earnings.
     * @param commonStock                            Common stock.
     * @param commonStockSharesOutstanding           Common stock shares outstanding.
     */
    public BalanceSheet(
            LocalDate fiscalDateEnding, BigDecimal totalAssets,
            BigDecimal totalCurrentAssets, BigDecimal cashAndCashEquivalentsAtCarryingValue,
            BigDecimal cashAndShortTermInvestments, BigDecimal inventory,
            BigDecimal currentNetReceivables, BigDecimal totalNonCurrentAssets,
            BigDecimal propertyPlantEquipment, BigDecimal accumulatedDepreciationAmortizationPPE,
            BigDecimal intangibleAssets, BigDecimal intangibleAssetsExcludingGoodwill,
            BigDecimal goodwill, BigDecimal investments,
            BigDecimal longTermInvestments, BigDecimal shortTermInvestments,
            BigDecimal otherCurrentAssets, BigDecimal otherNonCurrentAssets,
            BigDecimal totalLiabilities, BigDecimal totalCurrentLiabilities,
            BigDecimal currentAccountsPayable, BigDecimal deferredRevenue,
            BigDecimal currentDebt, BigDecimal shortTermDebt,
            BigDecimal totalNonCurrentLiabilities, BigDecimal capitalLeaseObligations,
            BigDecimal longTermDebt, BigDecimal currentLongTermDebt,
            BigDecimal longTermDebtNoncurrent, BigDecimal shortLongTermDebtTotal,
            BigDecimal otherCurrentLiabilities, BigDecimal otherNonCurrentLiabilities,
            BigDecimal totalShareholderEquity, BigDecimal treasuryStock,
            BigDecimal retainedEarnings, BigDecimal commonStock,
            BigDecimal commonStockSharesOutstanding) {

        this.fiscalDateEnding = fiscalDateEnding;
        this.totalAssets = totalAssets;
        this.totalCurrentAssets = totalCurrentAssets;
        this.cashAndCashEquivalentsAtCarryingValue = cashAndCashEquivalentsAtCarryingValue;
        this.cashAndShortTermInvestments = cashAndShortTermInvestments;
        this.inventory = inventory;
        this.currentNetReceivables = currentNetReceivables;
        this.totalNonCurrentAssets = totalNonCurrentAssets;
        this.propertyPlantEquipment = propertyPlantEquipment;
        this.accumulatedDepreciationAmortizationPPE = accumulatedDepreciationAmortizationPPE;
        this.intangibleAssets = intangibleAssets;
        this.intangibleAssetsExcludingGoodwill = intangibleAssetsExcludingGoodwill;
        this.goodwill = goodwill;
        this.investments = investments;
        this.longTermInvestments = longTermInvestments;
        this.shortTermInvestments = shortTermInvestments;
        this.otherCurrentAssets = otherCurrentAssets;
        this.otherNonCurrentAssets = otherNonCurrentAssets;
        this.totalLiabilities = totalLiabilities;
        this.totalCurrentLiabilities = totalCurrentLiabilities;
        this.currentAccountsPayable = currentAccountsPayable;
        this.deferredRevenue = deferredRevenue;
        this.currentDebt = currentDebt;
        this.shortTermDebt = shortTermDebt;
        this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
        this.capitalLeaseObligations = capitalLeaseObligations;
        this.longTermDebt = longTermDebt;
        this.currentLongTermDebt = currentLongTermDebt;
        this.longTermDebtNoncurrent = longTermDebtNoncurrent;
        this.shortLongTermDebtTotal = shortLongTermDebtTotal;
        this.otherCurrentLiabilities = otherCurrentLiabilities;
        this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
        this.totalShareholderEquity = totalShareholderEquity;
        this.treasuryStock = treasuryStock;
        this.retainedEarnings = retainedEarnings;
        this.commonStock = commonStock;
        this.commonStockSharesOutstanding = commonStockSharesOutstanding;
    }

    public LocalDate getFiscalDateEnding() { return fiscalDateEnding; }
    public BigDecimal getTotalAssets() { return totalAssets; }
    public BigDecimal getTotalCurrentAssets() { return totalCurrentAssets; }
    public BigDecimal getCashAndCashEquivalentsAtCarryingValue() { return cashAndCashEquivalentsAtCarryingValue; }
    public BigDecimal getCashAndShortTermInvestments() { return cashAndShortTermInvestments; }
    public BigDecimal getInventory() { return inventory; }
    public BigDecimal getCurrentNetReceivables() { return currentNetReceivables; }
    public BigDecimal getTotalNonCurrentAssets() { return totalNonCurrentAssets; }
    public BigDecimal getPropertyPlantEquipment() { return propertyPlantEquipment; }
    public BigDecimal getAccumulatedDepreciationAmortizationPPE() { return accumulatedDepreciationAmortizationPPE; }
    public BigDecimal getIntangibleAssets() { return intangibleAssets; }
    public BigDecimal getIntangibleAssetsExcludingGoodwill() { return intangibleAssetsExcludingGoodwill; }
    public BigDecimal getGoodwill() { return goodwill; }
    public BigDecimal getInvestments() { return investments; }
    public BigDecimal getLongTermInvestments() { return longTermInvestments; }
    public BigDecimal getShortTermInvestments() { return shortTermInvestments; }
    public BigDecimal getOtherCurrentAssets() { return otherCurrentAssets; }
    public BigDecimal getOtherNonCurrentAssets() { return otherNonCurrentAssets; }
    public BigDecimal getTotalLiabilities() { return totalLiabilities; }
    public BigDecimal getTotalCurrentLiabilities() { return totalCurrentLiabilities; }
    public BigDecimal getCurrentAccountsPayable() { return currentAccountsPayable; }
    public BigDecimal getDeferredRevenue() { return deferredRevenue; }
    public BigDecimal getCurrentDebt() { return currentDebt; }
    public BigDecimal getShortTermDebt() { return shortTermDebt; }
    public BigDecimal getTotalNonCurrentLiabilities() { return totalNonCurrentLiabilities; }
    public BigDecimal getCapitalLeaseObligations() { return capitalLeaseObligations; }
    public BigDecimal getLongTermDebt() { return longTermDebt; }
    public BigDecimal getCurrentLongTermDebt() { return currentLongTermDebt; }
    public BigDecimal getLongTermDebtNoncurrent() { return longTermDebtNoncurrent; }
    public BigDecimal getShortLongTermDebtTotal() { return shortLongTermDebtTotal; }
    public BigDecimal getOtherCurrentLiabilities() { return otherCurrentLiabilities; }
    public BigDecimal getOtherNonCurrentLiabilities() { return otherNonCurrentLiabilities; }
    public BigDecimal getTotalShareholderEquity() { return totalShareholderEquity; }
    public BigDecimal getTreasuryStock() { return treasuryStock; }
    public BigDecimal getRetainedEarnings() { return retainedEarnings; }
    public BigDecimal getCommonStock() { return commonStock; }
    public BigDecimal getCommonStockSharesOutstanding() { return commonStockSharesOutstanding; }
}
