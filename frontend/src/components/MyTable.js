import React from 'react';
import '../static/css/stockChartsStyle.css';

const MyTable = ({ incomeStatement }) => {
//console.log(incomeStatement); //Log
  
// Headers for Income Statement Table
const headersIncomeStatement = [
    { displayName: 'Fiscal Year End Date', dataName: 'fiscalDateEnding' },
    { displayName: 'Gross Profit', dataName: 'grossProfit' },
    { displayName: 'Total Revenue', dataName: 'totalRevenue' },
    { displayName: 'Cost of Revenue', dataName: 'costOfRevenue' },
    { displayName: 'Cost of Goods and Services Sold', dataName: 'costofGoodsAndServicesSold' },
    { displayName: 'Operating Income', dataName: 'operatingIncome' },
    { displayName: 'Selling, General and Administrative', dataName: 'sellingGeneralAndAdministrative' },
    { displayName: 'Research and Development', dataName: 'researchAndDevelopment' },
    { displayName: 'Operating Expenses', dataName: 'operatingExpenses' },
    { displayName: 'Investment Income Net', dataName: 'investmentIncomeNet' },
    { displayName: 'Net Interest Income', dataName: 'netInterestIncome' },
    { displayName: 'Interest Income', dataName: 'interestIncome' },
    { displayName: 'Interest Expense', dataName: 'interestExpense' },
    { displayName: 'Non-Interest Income', dataName: 'nonInterestIncome' },
    { displayName: 'Other Non-Operating Income', dataName: 'otherNonOperatingIncome' },
    { displayName: 'Depreciation', dataName: 'depreciation' },
    { displayName: 'Depreciation and Amortization', dataName: 'depreciationAndAmortization' },
    { displayName: 'Income Before Tax', dataName: 'incomeBeforeTax' },
    { displayName: 'Income Tax Expense', dataName: 'incomeTaxExpense' },
    { displayName: 'Interest and Debt Expense', dataName: 'interestAndDebtExpense' },
    { displayName: 'Net Income From Continuing Operations', dataName: 'netIncomeFromContinuingOperations' },
    { displayName: 'Comprehensive Income Net of Tax', dataName: 'comprehensiveIncomeNetOfTax' },
    { displayName: 'EBIT', dataName: 'ebit' },
    { displayName: 'EBITDA', dataName: 'ebitda' },
    { displayName: 'Net Income', dataName: 'netIncome' },
];

// Headers for Balance Sheet Table
const headersBalanceSheet = [
    { displayName: 'Fiscal Year End Date', dataName: 'fiscalDateEnding' },
    { displayName: 'Reported Currency', dataName: 'reportedCurrency' },
    { displayName: 'Operating Cashflow', dataName: 'operatingCashflow' },
    { displayName: 'Payments for Operating Activities', dataName: 'paymentsForOperatingActivities' },
    { displayName: 'Proceeds from Operating Activities', dataName: 'proceedsFromOperatingActivities' },
    { displayName: 'Change in Operating Liabilities', dataName: 'changeInOperatingLiabilities' },
    { displayName: 'Change in Operating Assets', dataName: 'changeInOperatingAssets' },
    { displayName: 'Depreciation, Depletion and Amortization', dataName: 'depreciationDepletionAndAmortization' },
    { displayName: 'Capital Expenditures', dataName: 'capitalExpenditures' },
    { displayName: 'Change in Receivables', dataName: 'changeInReceivables' },
    { displayName: 'Change in Inventory', dataName: 'changeInInventory' },
    { displayName: 'Profit/Loss', dataName: 'profitLoss' },
    { displayName: 'Cashflow from Investment', dataName: 'cashflowFromInvestment' },
    { displayName: 'Cashflow from Financing', dataName: 'cashflowFromFinancing' },
    { displayName: 'Proceeds from Repayments of Short Term Debt', dataName: 'proceedsFromRepaymentsOfShortTermDebt' },
    { displayName: 'Payments for Repurchase of Common Stock', dataName: 'paymentsForRepurchaseOfCommonStock' },
    { displayName: 'Payments for Repurchase of Equity', dataName: 'paymentsForRepurchaseOfEquity' },
    { displayName: 'Payments for Repurchase of Preferred Stock', dataName: 'paymentsForRepurchaseOfPreferredStock' },
    { displayName: 'Dividend Payout', dataName: 'dividendPayout' },
    { displayName: 'Dividend Payout (Common Stock)', dataName: 'dividendPayoutCommonStock' },
    { displayName: 'Dividend Payout (Preferred Stock)', dataName: 'dividendPayoutPreferredStock' },
    { displayName: 'Proceeds from Issuance of Common Stock', dataName: 'proceedsFromIssuanceOfCommonStock' },
    { displayName: 'Proceeds from Issuance of Long Term Debt and Capital Securities (Net)', dataName: 'proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet' },
    { displayName: 'Proceeds from Issuance of Preferred Stock', dataName: 'proceedsFromIssuanceOfPreferredStock' },
    { displayName: 'Proceeds from Repurchase of Equity', dataName: 'proceedsFromRepurchaseOfEquity' },
    { displayName: 'Proceeds from Sale of Treasury Stock', dataName: 'proceedsFromSaleOfTreasuryStock' },
    { displayName: 'Change in Cash and Cash Equivalents', dataName: 'changeInCashAndCashEquivalents' },
    { displayName: 'Change in Exchange Rate', dataName: 'changeInExchangeRate' },
    { displayName: 'Net Income', dataName: 'netIncome' },
];

// Headers for Cash Flow Table
const headersCashFlow = [
    { displayName: 'Fiscal Year End Date', dataName: 'fiscalDateEnding' },
    { displayName: 'Reported Currency', dataName: 'reportedCurrency' },
    { displayName: 'Operating Cashflow', dataName: 'operatingCashflow' },
    { displayName: 'Payments for Operating Activities', dataName: 'paymentsForOperatingActivities' },
    { displayName: 'Proceeds from Operating Activities', dataName: 'proceedsFromOperatingActivities' },
    { displayName: 'Change in Operating Liabilities', dataName: 'changeInOperatingLiabilities' },
    { displayName: 'Change in Operating Assets', dataName: 'changeInOperatingAssets' },
    { displayName: 'Depreciation, Depletion and Amortization', dataName: 'depreciationDepletionAndAmortization' },
    { displayName: 'Capital Expenditures', dataName: 'capitalExpenditures' },
    { displayName: 'Change in Receivables', dataName: 'changeInReceivables' },
    { displayName: 'Change in Inventory', dataName: 'changeInInventory' },
    { displayName: 'Profit/Loss', dataName: 'profitLoss' },
    { displayName: 'Cashflow from Investment', dataName: 'cashflowFromInvestment' },
    { displayName: 'Cashflow from Financing', dataName: 'cashflowFromFinancing' },
    { displayName: 'Proceeds from Repayments of Short Term Debt', dataName: 'proceedsFromRepaymentsOfShortTermDebt' },
    { displayName: 'Payments for Repurchase of Common Stock', dataName: 'paymentsForRepurchaseOfCommonStock' },
    { displayName: 'Payments for Repurchase of Equity', dataName: 'paymentsForRepurchaseOfEquity' },
    { displayName: 'Payments for Repurchase of Preferred Stock', dataName: 'paymentsForRepurchaseOfPreferredStock' },
    { displayName: 'Dividend Payout', dataName: 'dividendPayout' },
    { displayName: 'Dividend Payout (Common Stock)', dataName: 'dividendPayoutCommonStock' },
    { displayName: 'Dividend Payout (Preferred Stock)', dataName: 'dividendPayoutPreferredStock' },
    { displayName: 'Proceeds from Issuance of Common Stock', dataName: 'proceedsFromIssuanceOfCommonStock' },
    { displayName: 'Proceeds from Issuance of Long Term Debt and Capital Securities (Net)', dataName: 'proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet' },
    { displayName: 'Proceeds from Issuance of Preferred Stock', dataName: 'proceedsFromIssuanceOfPreferredStock' },
    { displayName: 'Proceeds from Repurchase of Equity', dataName: 'proceedsFromRepurchaseOfEquity' },
    { displayName: 'Proceeds from Sale of Treasury Stock', dataName: 'proceedsFromSaleOfTreasuryStock' },
    { displayName: 'Change in Cash and Cash Equivalents', dataName: 'changeInCashAndCashEquivalents' },
    { displayName: 'Change in Exchange Rate', dataName: 'changeInExchangeRate' },
    { displayName: 'Net Income', dataName: 'netIncome' },
];

return (
    <div className='customTable'>
      <div className='customTableColumnHeader'>
          {headersIncomeStatement.map((header) => (
              <div className='customTableCellHeader' key={header.dataName}>
                  {header.displayName}
              </div>
          ))}
      </div>

      {incomeStatement.map((item, rowIndex) => (
          <div className='customTableColumn' key={rowIndex}>
              {headersIncomeStatement.map((header, colIndex) => (
                  <div className='customTableCell' key={colIndex}>
                      {header.dataName === 'fiscalDateEnding' ? (
                          <div style={{ textAlign: 'center' }}>{item[header.dataName]}</div>
                      ) : (
                          item[header.dataName]
                      )}
                  </div>
              ))}
          </div>
        ))}
    </div>
  );
};

export default MyTable;