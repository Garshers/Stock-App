import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function StockChart() {
    const { symbol } = useParams();
    //console.log("Symbol in StockChart:", symbol); // Log

    // State variables to store stock data, annual report, and loading states
    const [stocks, setStocks] = useState([]);
    const [report, setReport] = useState(null);
    const [loadingStocks, setLoadingStocks] = useState(true);
    const [loadingReport, setLoadingReport] = useState(false);

    // useEffect hook to fetch stock data when the component mounts or the symbol changes
    useEffect(() => {
        const fetchStockData = async () => {
            try {
                const url = `http://localhost:8080/api/stockCharts/${symbol}/stocks`;
                //console.log("Fetching from:", url); // Log
    
                const response = await axios.get(url);
                //console.log("Response:", response); // Log
                //console.log("Response data:", response.data); // Log
    
                setStocks(response.data);
            } catch (error) {
                console.error("Error fetching stocks:", error);
            } finally {
                setLoadingStocks(false);
            }
        };
    
        fetchStockData();
    }, [symbol]);

    // Function to fetch the annual report data
    const fetchReportData = async () => {
        setLoadingReport(true);
        try {
            const response = await axios.get(`http://localhost:8080/api/stockCharts/${symbol}/reports`);
            setReport(response.data);
        } catch (error) {
            console.error("Error fetching report:", error);
        } finally {
            setLoadingReport(false);
        }
    };

    return (
        <div>
            {/* Display stock data */}
            <h2>Stocks</h2>
            {loadingStocks ? <div>Loading stocks...</div> : (
                <ul>
                    {stocks.map(stock => (
                        <li key={stock.date}>
                            {stock.date},
                            {stock.price}
                        </li>
                    ))}
                </ul>
            )}

            {/* Button to fetch the annual report */}
            <button onClick={fetchReportData} disabled={loadingReport}> {/* Disable button while loading */}
                {loadingReport ? "Loading report..." : "Get Annual Report"} {/* Display loading message on button */}
            </button>

            {/* Conditionally display the annual report */}
            {report && ( // Only render the report if it exists (i.e., has been fetched)
                <div>
                    <h2>Annual Report</h2>
                    <ul>
                        {report.map(item => (
                            <li key={item.fiscalDateEnding}>
                                {item.fiscalDateEnding},
                                {item.grossProfit} {/*So on...*/}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default StockChart;