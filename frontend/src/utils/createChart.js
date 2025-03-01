import { Chart } from 'chart.js/auto';

/**
 * Creates a chart based on the provided data and options.
 *
 * @param {CanvasRenderingContext2D} ctx - The canvas context to draw the chart on.
 * @param {string} type - The type of chart (e.g., 'line', 'bar', 'pie').
 * @param {string[]} labels - An array of labels for the X-axis.
 * @param {number[]} data - An array of data to be displayed on the chart.
 * @param {string} dataName - The name of the dataset, displayed in the legend.
 * @param {object} [options={}] - Optional chart configuration options.
 * @returns {Chart|null} - The chart object, or null if the canvas context is invalid.
 */
export function createChart(ctx, type, labels, data, dataName, options = {}) {
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