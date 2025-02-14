import React from 'react';
import '../static/css/stockChartsStyle.css';

const MyTable = ({ incomeStatement }) => {
  const headers = [
    'Fiscal Date Ending',
    'Currency',
    'Gross Profit',
    'Total Revenue',
    'Operating Income',
    'Net Income',
  ];

  return (
    <div className='customTable'>
      <div className='customTableRow'>
        {headers.map((header) => (
          <div className='customTableCell' key={header}>{header}</div>
        ))}
      </div>

      {incomeStatement.map((item, index) => (
        <div className='customTableRow' key={index}>
          <div className='customTableCell'>{item.fiscalDateEnding}</div>
          <div className='customTableCell'>{item.reportedCurrency}</div>
          <div className='customTableCell'>{item.grossProfit}</div>
          <div className='customTableCell'>{item.totalRevenue}</div>
          <div className='customTableCell'>{item.operatingIncome}</div>
          <div className='customTableCell'>{item.netIncome}</div>
        </div>
      ))}
    </div>
  );
};

export default MyTable;