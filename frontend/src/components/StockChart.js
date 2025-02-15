import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Chart } from 'chart.js/auto';
import '../static/css/headerFooter.css';
import '../static/css/stockChartsStyle.css';
import MyTable from './MyTable.js';

function StockChart() {
    const { symbol } = useParams();

    const [stocks, setStocks] = useState([]);
    const [incomeStatement, setIncomeStatement] = useState(null);
    const [overviewData, setOverviewData] = useState(null);

    const [loadingStocks, setLoadingStocks] = useState(true);
    const [loadingIncomeStatement, setLoadingIncomeStatement] = useState(false);
    const [loadingOverview, setLoadingOverview] = useState(true);

    const [selectedData, setSelectedData] = useState('netIncome');

    const [stockChart, setStockChart] = useState(null);
    const [incomeStatementChart, setIncomeStatementChart] = useState(null);

    {/* Fetches stock data from API */}
    useEffect(() => {
        const fetchStockData = async () => {
            try {
                const url = `http://localhost:8080/api/stockCharts/${symbol}/stocks`;
                const response = await axios.get(url);
                setStocks(response.data);
            } catch (error) {
                console.error("Error fetching stocks:", error);
            } finally {
                setLoadingStocks(false);
            }
        };

        fetchStockData();
    }, [symbol]);

    {/* Fetches income statement data from API */}
    const fetchIncomeStatementData = async () => {
        setLoadingIncomeStatement(true);
        try {
            const response = await axios.get(`http://localhost:8080/api/stockCharts/${symbol}/incomeStatement`);
            setIncomeStatement(response.data);
            console.log(response.data);
        } catch (error) {
            console.error("Error fetching financial statement:", error);
        } finally {
            setLoadingIncomeStatement(false);
        }
    };

    {/* Fetches overview data from API */}
    useEffect(() => {
        const fetchOverviewData = async () => {
            setLoadingOverview(true);
            try {
                const response = await axios.get(`http://localhost:8080/api/stockCharts/${symbol}/overview`);
                setOverviewData(response.data);
            } catch (error) {
                console.error("Error fetching overview data:", error);
            } finally {
                setLoadingOverview(false);
            }
        };

        fetchOverviewData();
    }, [symbol]);

    {/* Creates stock chart */}
    useEffect(() => {
        if (stocks.length > 0) {
            const labels = stocks.map(stock => stock.date);
            const data = stocks.map(stock => stock.price);
            const ctx = document.getElementById('stockChart').getContext('2d');

            const newStockChart = createChart(ctx, 'line', labels, data, 'Price (USD)');
            
            setStockChart(newStockChart);
        }
    }, [stocks]);

    {/* Creates Income Statement chart */}
    useEffect(() => {
        if (incomeStatement && incomeStatement.length > 0) {
            const frlabels = incomeStatement.map(item => item.fiscalDateEnding);
            const frdata = incomeStatement.map(item => item[selectedData]);
            const frdataName = selectedData;
            const ctx = document.getElementById('incomeStatementChart').getContext('2d');

            const newIncomeStatementChart = createChart(ctx, 'bar', frlabels, frdata, frdataName);

            setIncomeStatementChart(newIncomeStatementChart);
        }
    }, [incomeStatement, selectedData]); // Runs when those are changed

    {/* Supports changes of data selection dropdown */}
    const handleDataSelectionChange = (event) => {
        setSelectedData(event.target.value);
    };

    return (
        <div>
            <div className="contentBox">
                <div className="mainLeftBox"></div>
                <div className="mainCenterBox">
                    <h2>Stocks</h2>

                    {/* Stock price chart */}
                    {loadingStocks ? <div>Loading stocks...</div> : <canvas id="stockChart"></canvas>}

                    {/* Button for getting income statement data */}
                    <button onClick={fetchIncomeStatementData} disabled={loadingIncomeStatement}>
                        {loadingIncomeStatement ? "Loading report..." : "Get Income Statement"}
                    </button>

                    {/* Box containing income statement (IS) data */}
                    {incomeStatement && incomeStatement.length > 0 ? (
                        <div className='incomeStatementBox'>
                            <h2>Annual Report</h2>
                            {/* Chart and chart selection section */}
                            <select id="dataSelection" value={selectedData} onChange={handleDataSelectionChange}>
                                <option value="netIncome">Net Income</option>
                                <option value="grossProfit">Gross Profit</option>
                                <option value="totalRevenue">Total Revenue</option>
                                <option value="operatingIncome">Operating Income</option>
                            </select>
                            <canvas id="incomeStatementChart"></canvas>

                            {/* Custom table showing IS data */}
                            <MyTable incomeStatement={incomeStatement} />
                        </div>
                    ) : loadingIncomeStatement ? (
                        <div>Loading income statement data...</div>
                    ) : (
                        <div>No annual income statement data available.</div>
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
                                    .replace(/([A-Z])(?=[A-Z][a-z])/g, '$1 ')             // Add space between a capital letter followed by another capital and a lowercase letter (EvToEBITDA -> Ev To EBITDA)
                                    .replace(/([A-Z]+)(?=[A-Z][a-z]|$)/g, '$1 ')          // Add space after an acronym, unless it's followed by another capital and a lowercase letter or the end of the string (DividendPerShare -> Dividend Per Share)
                                    .replace(/([a-z])([A-Z]+)(?=[A-Z][a-z]|$)/g, '$1 $2') // Add space between a lowercase letter and an acronym, unless the acronym is followed by another capital letter and a lowercase letter or the end of the string (adjustedEBITDA -> Adjusted EBITDA)
                                    .replace(/^./, str => str.toUpperCase());             // Capitalize the first letter

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