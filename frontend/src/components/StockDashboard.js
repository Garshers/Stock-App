import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Chart } from 'chart.js/auto';
import '../assets/stockDashboardStyle.css';
import CustomTable from './CustomTable.js';
import { fetchData } from '../services/StockAPIService.js';
import * as headers from '../utils/FinancialDataHeaders.js';

function StockChart() {
    const { symbol } = useParams();

    const [dcfData, setDcfData] = useState(Array(10).fill(0));

    const [stocks, setStocks] = useState([]);
    const [incomeStatement, setIncomeStatement] = useState(null);
    const [balanceSheet, setBalanceSheet] = useState(null);
    const [cashFlowStatement, setCashFlowStatement] = useState(null);
    const [overviewData, setOverviewData] = useState(null);

    const [loadingStocks, setLoadingStocks] = useState(true);
    const [loadingBalanceSheet, setLoadingIncomeStatement] = useState(false);
    const [loadingIncomeStatement, setLoadingBalanceSheet] = useState(false);
    const [loadingCashFlowStatement, setLoadingCashFlowStatement] = useState(false);
    const [loadingOverview, setLoadingOverview] = useState(true);

    const [selectedData, setSelectedData] = useState('netIncome');

    const [stockChart, setStockChart] = useState(null);
    const [incomeStatementChart, setIncomeStatementChart] = useState(null);

    /**
     * useEffect hook to fetch stock data from the API.
     * Runs when the stock symbol changes.
     */
    useEffect(() => {
        const fetchStockData = async () => {
            try {
                const data = await fetchData(`${symbol}/stocks`);
                setStocks(data);
            } catch (error) {
                console.error("Error fetching stocks:", error);
            } finally {
                setLoadingStocks(false);
            }
        };

        fetchStockData();
    }, [symbol]);

    /**
     * Universal function to fetch annual report data from the API.
     */
    const fetchDataAndSetState = async (tableKey, setState, setLoadingState) => {
        setLoadingState(true);
        try {
            const data = await fetchData(`${symbol}/${tableKey}`);
            setState(data);
        } catch (error) {
            console.error(`Error fetching ${tableKey}:`, error);
        } finally {
            setLoadingState(false);
        }
    };
    
    /**
     * Async function to fetch income statement data from the API.
     */
    const fetchIncomeStatementData = async () => {
        await fetchDataAndSetState("incomeStatement", setIncomeStatement, setLoadingIncomeStatement);
    };
    
    /**
     * Async function to fetch balance sheet data from the API.
     */
    const fetchBalanceSheetData = async () => {
        await fetchDataAndSetState("balanceSheet", setBalanceSheet, setLoadingBalanceSheet);
    };
    
    /**
     * Async function to fetch cash flow statement data from the API.
     */
    const fetchCashFlowStatementData = async () => {
        await fetchDataAndSetState("cashFlowS", setCashFlowStatement, setLoadingCashFlowStatement);
    };

    /**
     * useEffect hook to fetch overview data from the API.
     * Runs when the stock symbol changes.
     */
    useEffect(() => {
        const fetchOverviewData = async () => {
            setLoadingOverview(true);
            try {
                const data = await fetchData(`${symbol}/overview`);
                setOverviewData(data);
            } catch (error) {
                console.error("Error fetching overview data:", error);
            } finally {
                setLoadingOverview(false);
            }
        };

        fetchOverviewData();
    }, [symbol]);

    /**
     * useEffect hook to create the stock chart.
     * Runs when the stock data changes.
     */
    useEffect(() => {
        if (stocks.length > 0) {
            const labels = stocks.map(stock => stock.date);
            const data = stocks.map(stock => stock.price);
            const ctx = document.getElementById('stockChart').getContext('2d');

            const newStockChart = createChart(ctx, 'line', labels, data, 'Price (USD)');
            
            setStockChart(newStockChart);
        }
    }, [stocks]);

    /**
     * useEffect hook to create the income statement chart.
     * Runs when the income statement data or selected data changes.
     */
    useEffect(() => {
        if (incomeStatement && incomeStatement.length > 0) {
            const frlabels = incomeStatement.map(item => item.fiscalDateEnding);
            const frdata = incomeStatement.map(item => item[selectedData]);
            const frdataName = selectedData;
            const ctx = document.getElementById('incomeStatementChart').getContext('2d');

            frlabels.reverse();
            frdata.reverse();

            const newIncomeStatementChart = createChart(ctx, 'bar', frlabels, frdata, frdataName);

            setIncomeStatementChart(newIncomeStatementChart);
        }
    }, [incomeStatement, selectedData]);

    /**
     * Function to handle the change of data selection in the dropdown.
     * @param {Event} event - The change event.
     */
    const handleDataSelectionChange = (event) => {
        setSelectedData(event.target.value);
    };

    /**
     * Function to handle the change of values in the input field.
     * @param {Event} event - The change event.
     * @param {number} index - Index of input List.
     */
    const handleChange = (index, event) => {
        const inputValue = event.target.value;
        const parsedNumber = parseFloat(inputValue);

        const newDcfData = [...dcfData];
        newDcfData[index] = isNaN(parsedNumber) ? 0 : parsedNumber;
        setDcfData(newDcfData);
    };

    /**
     * Async function to submit data to the backend.
     */
    const handleSubmit = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/dcfData', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ dcfData }),
            });

            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(errorData || `HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log('Success:', data);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div>
            <div className="contentBox">
                <div className="mainLeftBox"></div>
                <div className="mainCenterBox">
                    {/* Stock price chart */}
                    {loadingStocks ? <div>Loading stocks...</div> : <canvas id="stockChart"></canvas>}

                    {/* Input value block */}
                    <div>
                        {dcfData.map((number, index) => (
                            <div key={index}>
                                <label htmlFor={`number-${index}`}>Enter value {index + 1}:</label>
                                <input
                                    type="text"
                                    value={number}
                                    onChange={(event) => handleChange(index, event)}
                                />
                            </div>
                        ))}
                        <button onClick={handleSubmit}>Send</button>
                    </div>

                    {/* Buttons for getting annual report data */}
                    <button onClick={fetchIncomeStatementData} disabled={loadingIncomeStatement}>
                        {loadingIncomeStatement ? "Loading report..." : "Get Income Statement"}
                    </button>
                    <button onClick={fetchBalanceSheetData} disabled={loadingBalanceSheet}>
                        {loadingBalanceSheet ? "Loading report..." : "Get Balance Sheet"}
                    </button>
                    <button onClick={fetchCashFlowStatementData} disabled={loadingCashFlowStatement}>
                        {loadingCashFlowStatement ? "Loading report..." : "Get Cash Flow Statement"}
                    </button>

                    {/* Box containing income statement (IS) data */}
                    {incomeStatement && incomeStatement.length > 0 ? (
                        <div className='annualDataBox'>
                            <h2>Annual Income Statement</h2>
                            {/* Chart and chart selection section */}
                            <select id="dataSelection" value={selectedData} onChange={handleDataSelectionChange}>
                                {headers.headersIncomeStatement.map((header) => (
                                    <option key={header.dataName} value={header.dataName} disabled={header.dataName === 'fiscalDateEnding'}>
                                        {header.displayName}
                                    </option>
                                ))}
                            </select>
                            <canvas id="incomeStatementChart"></canvas>

                            {/* Custom table showing IS data */}
                            <CustomTable tableData={incomeStatement} headers={headers.headersIncomeStatement}/>
                        </div>
                    ) : loadingIncomeStatement ? (
                        <div>Loading income statement data...</div>
                    ) : (
                        <div>No annual income statement data available.</div>
                    )}

                    {/* Box containing balance sheet (BS) data */}
                    {balanceSheet && balanceSheet.length > 0 ? (
                        <div className='annualDataBox'>
                            <h2>Annual Balance Sheet</h2>
                            {/* Chart and chart selection section */}
                            <select id="dataSelection" value={selectedData} onChange={handleDataSelectionChange}>
                                {headers.headersBalanceSheet.map((header) => (
                                    <option key={header.dataName} value={header.dataName} disabled={header.dataName === 'fiscalDateEnding'}>
                                        {header.displayName}
                                    </option>
                                ))}
                            </select>
                            <canvas id="balanceSheetChart"></canvas>

                            {/* Custom table showing IS data */}
                            <CustomTable tableData={balanceSheet} headers={headers.headersBalanceSheet}/>
                        </div>
                    ) : loadingBalanceSheet ? (
                        <div>Loading balance sheet data...</div>
                    ) : (
                        <div>No annual balance sheet data available.</div>
                    )}

                    {/* Box containing cash flow statement (CFS) data */}
                    {cashFlowStatement && cashFlowStatement.length > 0 ? (
                        <div className='annualDataBox'>
                            <h2>Annual cash flow statement</h2>
                            {/* Chart and chart selection section */}
                            <select id="dataSelection" value={selectedData} onChange={handleDataSelectionChange}>
                                {headers.headersCashFlowStatement.map((header) => (
                                    <option key={header.dataName} value={header.dataName} disabled={header.dataName === 'fiscalDateEnding'}>
                                        {header.displayName}
                                    </option>
                                ))}
                            </select>
                            <canvas id="cashFlowStatementChart"></canvas>

                            {/* Custom table showing IS data */}
                            <CustomTable tableData={cashFlowStatement} headers={headers.headersCashFlowStatement}/>
                        </div>
                    ) : loadingCashFlowStatement ? (
                        <div>Loading cash flow statement data...</div>
                    ) : (
                        <div>No annual cash flow statement data available.</div>
                    )}

                </div>
                <div className="mainRightBox">
                    {/* Overview Data Table */}
                    {loadingOverview ? (
                        <div>Loading overview data...</div>
                    ) : overviewData ? (
                        <div className="overviewTableBox">
                            <h2>Overview</h2>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Metric</th>
                                        <th>Value</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {Object.entries(overviewData)
                                    .filter(([key]) => key !== "description")
                                    .map(([key, value]) => {
                                    
                                    const formattedKey = key
                                        .replace(/([A-Z])([A-Z])(?=[a-z])|([a-z])([A-Z])/g, '$1$3 $2$4')// Add space between capital letters followed by a lowercase, or between a lowercase and a capital
                                        .replace(/([A-Z]+)([A-Z][a-z])/g, '$1 $2')                      // Add space between acronyms and a capital letter followed by a lowercase
                                        .replace(/([a-zA-Z])(\d+)/g, '$1 $2')                           // Add space between a letter and a number
                                        .replace(/(\d+)([a-zA-Z])/g, '$1 $2')                           // Add space between a number and a letter
                                        .replace(/^./, str => str.toUpperCase());                       // Capitalize the first letter

                                    return (
                                        <tr key={key}>
                                        <td style={{ textAlign: 'left' }}>{formattedKey}</td>
                                        <td>
                                            {key.includes("Date") && value ? new Date(value).toLocaleDateString() :
                                            key.includes("Yield") || key.includes("Ratio") || key.includes("Beta") || typeof value === 'number' ? value.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }) :
                                            value && typeof value === 'object' ? JSON.stringify(value) : value ? value.toString() : "-"}
                                        </td>
                                        </tr>
                                    );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    ) : (
                        <div>No overview data available.</div>
                    )}
                </div>
            </div>
        </div>
    );

    {/* Helper func in creating charts */}
    function createChart(ctx, type, labels, data, dataName, options = {}) {
        if (!ctx) {
            console.error("Canvas context is null or undefined.");
            return null; 
        }
    
        const existingChart = Chart.getChart(ctx);
        if (existingChart) {
            existingChart.destroy();
        }
    
        const chartData = {
            labels: labels,
            datasets: [{
                label: dataName,
                data: data,
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 2,
                pointRadius: 0,
                ...(type === 'bar' ? {} : { fill: true }), // "..." - Spread, unpacking object
            }]
        };
    
        const chartOptions = {
            responsive: true,
            ...options,
            scales: { 
                y: {
                    beginAtZero: true 
                }
            }
        };
    
        const newChart = new Chart(ctx, {
            type: type,
            data: chartData,
            options: chartOptions
        });
    
        return newChart;
    }
}

export default StockChart;