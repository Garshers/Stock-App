package com.stockapp.StockApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.StockApp.model.AnnualReport;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

/**
 * Controller for the Stock Chart related API endpoints.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StockChartsController {
    private final AlphaVantageService service = new AlphaVantageService();

    /**
     * Retrieves stock data for a given symbol.
     *
     * @param symbol The stock symbol.
     * @return A list of Stock objects representing the stock data.
     * @throws IllegalArgumentException If the provided symbol is null or empty.
     * @throws RuntimeException If an error occurs during data retrieval.
     */
    @GetMapping("/api/stockCharts/{symbol}/stocks")
    public List<Stock> getStockData(@PathVariable String symbol) {
        if (symbol == null || symbol.isEmpty()){
            throw new IllegalArgumentException("Please provide a stock symbol.");
        }
        URLCreator stockURL = new URLCreator(symbol, URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED);
        String url = stockURL.generateUrl();

        try {
            String jsonResponse = service.getStockData(url);
            return service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching stock data: " + e.getMessage());
            throw new RuntimeException("Error fetching stock data.", e);
        }
    }

    /**
     * Retrieves annual report data for a given symbol.
     *
     * @param symbol The stock symbol.
     * @return A list of AnnualReport objects representing the annual report data.
     * @throws RuntimeException If an error occurs during data retrieval.
     */
    @GetMapping("/api/stockCharts/{symbol}/reports")
    public List<AnnualReport> getAnnualReports(@PathVariable String symbol) {
        URLCreator stockReportURL = new URLCreator(symbol, URLCreator.FunctionType.INCOME_STATEMENT);
        String reportUrl = stockReportURL.generateUrl();

        try {
            String jsonResponse = service.getStockData(reportUrl);
            return service.parseAnnualReports(stockReportURL.getSymbol(), jsonResponse, stockReportURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching report data: " + e.getMessage());
            throw new RuntimeException("Error fetching report data.", e);
        }
    }
}