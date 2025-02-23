import React from 'react';
import '../assets/stockDashboardStyle.css';
import { headersIncomeStatement } from '../utils/FinancialDataHeaders.js';

const CustomTable = ({ tableData }) => {
//console.log(tableData); //Log
  
return (
    <div className='customTable'>
      <div className='customTableColumnHeader'>
          {headersIncomeStatement.map((header) => (
              <div className='customTableCellHeader' key={header.dataName}>
                  {header.displayName}
              </div>
          ))}
      </div>

      {tableData.map((item, rowIndex) => (
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

export default CustomTable;