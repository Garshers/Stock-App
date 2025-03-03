import { useEffect } from 'react';
import { createChart } from '../utils/createChart.js'

/**
 * React Hook that creates and updates a chart based on the provided data.
 *
 * @param {Array} data - The data array to be displayed on the chart.
 * @param {string} selectedData - The name of the selected data column to display.
 * @param {object} headers - An object containing information about the column headers.
 * @param {string} chartId - The identifier of the canvas element where the chart is to be drawn.
 * @param {string} chartType - The type of chart (e.g., 'line', 'bar', 'pie').
 * @param {function} setChart - A function to set the chart state in the parent component.
 */
const useReportChart = (data, type, selectedData, headers, chartId, chartType, setChart) => {
    useEffect(() => {
        if (data && data.length > 0) {
            const check = Object.values(headers[type]).flat().find(header => header.dataName === selectedData) ? true : false;
            selectedData = check ? selectedData : headers[type][1].dataName;

            const labels = data.map(item => item.fiscalDateEnding);
            const chartData = data.map(item => item[selectedData]);
            const selectedHeader = Object.values(headers).flat().find(header => header.dataName === selectedData);
            const dataName = selectedHeader ? selectedHeader.displayName : headers[type][1].dataName;
            const ctx = document.getElementById(chartId).getContext('2d');

            labels.reverse();
            chartData.reverse();

            const newChart = createChart(ctx, chartType, labels, chartData, dataName);

            setChart(newChart);
        }
    }, [data, type, selectedData, headers, chartId, chartType, setChart]);
};

export default useReportChart;