package com.stockapp.StockApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.StockApp.model.IncomeStatement;
import com.stockapp.StockApp.model.Overview;
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
        System.out.println("stock url: " + url);

        try {
            String jsonResponse = service.getStockData(url);
            return service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching stock data: " + e.getMessage());
            throw new RuntimeException("Error fetching stock data.", e);
        }
    }

    /**
     * Retrieves annual inclome statment data for a given symbol.
     *
     * @param symbol The stock symbol.
     * @return A list of Annual Income Statement objects representing the annual income statement data.
     * @throws RuntimeException If an error occurs during data retrieval.
     */
    @GetMapping("/api/stockCharts/{symbol}/overview")
    public Overview getOverview(@PathVariable String symbol) {
        URLCreator URL = new URLCreator(symbol, URLCreator.FunctionType.OVERVIEW);
        String url = URL.generateUrl();
        System.out.println("Overview url: " + url);

        try {
            String jsonResponse = service.getStockData(url);
            return service.parseOverview(URL.getSymbol(), jsonResponse, URL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching income statement data: " + e.getMessage());
            throw new RuntimeException("Error fetching income statement data.", e);
        }
    }

    /**
     * Retrieves annual inclome statment data for a given symbol.
     *
     * @param symbol The stock symbol.
     * @return A list of Annual Income Statement objects representing the annual income statement data.
     * @throws RuntimeException If an error occurs during data retrieval.
     */
    @GetMapping("/api/stockCharts/{symbol}/incomeStatement")
    public List<IncomeStatement> getAnnualIncomeStatements(@PathVariable String symbol) {
        URLCreator stockIncomeStatementURL = new URLCreator(symbol, URLCreator.FunctionType.INCOME_STATEMENT);
        String url = stockIncomeStatementURL.generateUrl();
        System.out.println("income statement url: " + url);

        try {
            String jsonResponse = service.getStockData(url);
            System.out.println(service.parseAnnualIncomeStatement(stockIncomeStatementURL.getSymbol(), jsonResponse, stockIncomeStatementURL.getFunction()) + " From controller"); // Log
            return service.parseAnnualIncomeStatement(stockIncomeStatementURL.getSymbol(), jsonResponse, stockIncomeStatementURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching income statement data: " + e.getMessage());
            throw new RuntimeException("Error fetching income statement data.", e);
        }
    }
}