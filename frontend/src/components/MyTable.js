import React from 'react';
import '../static/css/stockChartsStyle.css';

const MyTable = ({ incomeStatement }) => {
  const headers = [
    { displayName: 'Fiscal Year End Date', dataName: 'fiscalDateEnding' },
    { displayName: 'Gross Profit', dataName: 'grossProfit' },
    { displayName: 'Total Revenue', dataName: 'totalRevenue' },
    { displayName: 'Operating Income', dataName: 'operatingIncome' },
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