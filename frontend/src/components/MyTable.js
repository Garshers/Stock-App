import React from 'react';
import '../static/css/stockChartsStyle.css';

const MyTable = ({ incomeStatement }) => {
  //console.log(incomeStatement); //Log
  
  const headers = [
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

  return (
    <div className='customTable'>
      <div className='customTableColumnHeader'>
          {headers.map((header) => (
              <div className='customTableCellHeader' key={header.dataName}>
                  {header.displayName}
              </div>
          ))}
      </div>

      {incomeStatement.map((item, rowIndex) => (
          <div className='customTableColumn' key={rowIndex}>
              {headers.map((header, colIndex) => (
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