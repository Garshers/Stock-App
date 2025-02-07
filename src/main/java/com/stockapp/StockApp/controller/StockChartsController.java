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
 * Controller for the Stock Chart pages.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StockChartsController {

    /**
     * Retrieves stock data and annual reports for a given symbol and displays them on the stock charts page.
     *
     * @param symbol The stock symbol.
     * @param model  The Spring Model object for adding attributes to the view.
     * @return The name of the stock charts view ("stockCharts") or the error view ("error") if an error occurs.
     */
    @GetMapping("/api/stockCharts/{symbol}/stocks")
    public List<Stock> getStockData(@PathVariable String symbol) {
        if (symbol == null || symbol.isEmpty()){
            throw new IllegalArgumentException("Please provide a stock symbol.");
        }
        URLCreator stockURL = new URLCreator(symbol, URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED);
        String url = stockURL.generateUrl();

		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(url);
            return service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching stock data: " + e.getMessage());
            throw new RuntimeException("Error fetching stock data.", e);
        }
    }
        
    @GetMapping("/api/stockCharts/{symbol}/reports")
    public List<AnnualReport> getAnnualReports(@PathVariable String symbol) {
        URLCreator stockReportURL = new URLCreator(symbol, URLCreator.FunctionType.INCOME_STATEMENT);
        String reportUrl = stockReportURL.generateUrl();

        AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(reportUrl);
            return service.parseAnnualReports(stockReportURL.getSymbol(), jsonResponse, stockReportURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching report data: " + e.getMessage());
            throw new RuntimeException("Error fetching report data.", e);
        }
    }
}