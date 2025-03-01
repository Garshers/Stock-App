import { useEffect } from 'react';
import { createChart } from '../utils/createChart.js'

/**
 * Hook React, który tworzy i aktualizuje wykres na podstawie dostarczonych danych.
 *
 * @param {Array} data - Tablica danych do wyświetlenia na wykresie.
 * @param {string} selectedData - Nazwa wybranej kolumny danych do wyświetlenia.
 * @param {object} headers - Obiekt zawierający informacje o nagłówkach kolumn.
 * @param {string} chartId - Identyfikator elementu canvas, na którym ma być narysowany wykres.
 * @param {string} chartType - Typ wykresu (np. 'line', 'bar', 'pie').
 * @param {function} setChart - Funkcja ustawiająca stan wykresu w komponencie nadrzędnym.
 */
const useReportChart = (data, selectedData, headers, chartId, chartType, setChart) => {
    useEffect(() => {
        if (data && data.length > 0) {
            const labels = data.map(item => item.fiscalDateEnding);
            const chartData = data.map(item => item[selectedData]);
            const selectedHeader = Object.values(headers).flat().find(header => header.dataName === selectedData);
            const dataName = selectedHeader ? selectedHeader.displayName : selectedData;
            const ctx = document.getElementById(chartId).getContext('2d');

            labels.reverse();
            chartData.reverse();

            const newChart = createChart(ctx, chartType, labels, chartData, dataName);

            setChart(newChart);
        }
    }, [data, selectedData, headers, chartId, chartType, setChart]);
};

export default useReportChart;