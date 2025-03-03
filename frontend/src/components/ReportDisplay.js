import React, { useState, useEffect } from 'react';
import useReportChart from '../hooks/useReportChart.js';
import * as headers from '../utils/FinancialDataHeaders.js';
import CustomTable from './CustomTable.js';

function ReportDisplay({ reportData, reportType }) {
    const [selectedData, setSelectedData] = useState(headers[reportType][1].dataName);
    const [chart, setChart] = useState(null);
    const chartId = `${reportType}Chart`;

    const availableData = headers[reportType] || [];
    const formattedHeader = reportType.replace(/([a-z])([A-Z])/g, '$1 $2');

    useReportChart(
        reportData,
        reportType,
        selectedData,
        headers,
        chartId,
        'bar',
        setChart
    );

    /**
     * Function to handle the change of data selection in the dropdown.
     * @param {Event} event - The change event.
     */
    const handleDataSelectionChange = (event) => {
        setSelectedData(event.target.value);
    };

    return (
        <div className='annualDataBox'>
            <h2>Annual {formattedHeader.charAt(0).toUpperCase() + formattedHeader.slice(1)}</h2>
            
            {/* Canvas showing annual data for selected header*/}
            <select value={selectedData} onChange={handleDataSelectionChange}>
                {availableData.map(header => (
                    <option key={header.dataName} value={header.dataName} disabled={header.dataName === 'fiscalDateEnding'}>
                        {header.displayName}
                    </option>
                ))}
            </select>

            {/* Canvas showing annual data for selected header*/}
            <canvas id={chartId}></canvas>

            {/* Custom table showing annual data */}
            <CustomTable tableData={reportData} headers={availableData}/>
        </div>
    );
}

export default ReportDisplay;