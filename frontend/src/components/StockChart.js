import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Chart } from 'chart.js/auto';
import '../static/css/headerFooter.css';
import '../static/css/stockChartsStyle.css';

function StockChart() {
    const { symbol } = useParams();
    const [stocks, setStocks] = useState([]);
    const [incomeStatement, setIncomeStatement] = useState(null);
    const [loadingStocks, setLoadingStocks] = useState(true);
    const [loadingIncomeStatement, setLoadingIncomeStatement] = useState(false);
    const [selectedData, setSelectedData] = useState('netIncome');
    const [stockChart, setStockChart] = useState(null);
    const [incomeStatementChart, setIncomeStatementChart] = useState(null);


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

    const fetchIncomeStatementData = async () => {
        setLoadingIncomeStatement(true);
        try {
            const response = await axios.get(`http://localhost:8080/api/stockCharts/${symbol}/incomeStatement`);
            setIncomeStatement(response.data);
        } catch (error) {
            console.error("Error fetching financial statement:", error);
        } finally {
            setLoadingIncomeStatement(false);
        }
    };

    useEffect(() => {
        if (stocks.length > 0) {
            const labels = stocks.map(stock => stock.date);
            const data = stocks.map(stock => stock.price);

            if (stockChart) {
                stockChart.destroy();
            }

            const ctx = document.getElementById('stockChart').getContext('2d');
            const newStockChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Price (USD)',
                        data: data,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderWidth: 2,
                        fill: true,
                        pointRadius: 0
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: { title: { display: true, text: 'Price (USD)' } }
                    }
                }
            });
            setStockChart(newStockChart);
        }
    }, [stocks]);


    useEffect(() => {
        if (incomeStatement && incomeStatement.length > 0) {
            const frlabels = incomeStatement.map(item => item.fiscalDateEnding);
            const frdata = incomeStatement.map(item => item[selectedData]);
            const frdataName = selectedData;

            if (incomeStatementChart) {
                incomeStatementChart.destroy();
            }

            const ctx = document.getElementById('incomeStatementChart').getContext('2d');
            const newIncomeStatementChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: frlabels,
                    datasets: [{
                        label: frdataName,
                        data: frdata,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderWidth: 2,
                        fill: true,
                        pointRadius: 0
                    }]
                },
                options: {
                    responsive: true
                }
            });
            setIncomeStatementChart(newIncomeStatementChart);
        }
    }, [incomeStatement, selectedData]);


    const handleDataSelectionChange = (event) => {
        setSelectedData(event.target.value);
    };

    return (
        <div>
            <div className="contentBox">
                <div className="mainLeftBox"></div>
                <div className="mainCenterBox">
                    <h2>Stocks</h2>
                    {loadingStocks ? <div>Loading stocks...</div> : <canvas id="stockChart"></canvas>}

                    <button onClick={fetchIncomeStatementData} disabled={loadingIncomeStatement}>
                        {loadingIncomeStatement ? "Loading report..." : "Get Income Statement"}
                    </button>

                    {/*Box containing annual report data*/}
                    {incomeStatement && incomeStatement.length > 0 ? (
                        <div className='incomeStatementBox'>
                            <h2>Annual Report</h2>
                            <select id="dataSelection" value={selectedData} onChange={handleDataSelectionChange}>
                                <option value="netIncome">Net Income</option>
                                <option value="grossProfit">Gross Profit</option>
                                <option value="totalRevenue">Total Revenue</option>
                                <option value="operatingIncome">Operating Income</option>
                            </select>
                            <canvas id="incomeStatementChart"></canvas>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Fiscal Date Ending</th>
                                        <th>Currency</th>
                                        <th>Gross Profit</th>
                                        <th>Total Revenue</th>
                                        <th>Operating Income</th>
                                        <th>Net Income</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {incomeStatement.map(item => (
                                        <tr key={item.fiscalDateEnding}>
                                            <td>{item.fiscalDateEnding}</td>
                                            <td>{item.reportedCurrency}</td>
                                            <td>{item.grossProfit}</td>
                                            <td>{item.totalRevenue}</td>
                                            <td>{item.operatingIncome}</td>
                                            <td>{item.netIncome}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>

                        </div>
                    ) : loadingIncomeStatement ? (
                        <div>Loading income statement data...</div>
                    ) : (
                        <div>No annual income statement data available.</div>
                    )}

                </div>
                <div className="mainRightBox"></div>
            </div>
        </div>
    );
}

export default StockChart;