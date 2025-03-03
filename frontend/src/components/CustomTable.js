import React from 'react';
import './StockDashboard/stockDashboardStyle.css';

const CustomTable = ({ tableData, headers }) => {
//console.log(tableData); //Log
  
return (
    <div className='customTable'>
        <div className='customTableColumnHeader'>
            {headers.map((header) => (
                <div className='customTableCellHeader' key={header.dataName}>
                    {header.displayName}
                </div>
            ))}
        </div>

        {tableData.map((item, rowIndex) => (
            <div className='customTableColumn' key={rowIndex}>
                {headers.map((header, colIndex) => (
                    <div className='customTableCell' key={colIndex}>{item[header.dataName] || "-"}</div>
                ))}
          </div>
        ))}
    </div>
  );
};

export default CustomTable;