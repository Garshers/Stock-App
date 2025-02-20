package com.stockapp.StockApp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.StockApp.model.IncomeStatement;
import com.stockapp.StockApp.model.Overview;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;
import com.stockapp.StockApp.util.DCFValuationUtil;


/**
 * Rest Controller for the Stock Chart related API endpoints.
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
            return service.parseAnnualIncomeStatement(stockIncomeStatementURL.getSymbol(), jsonResponse, stockIncomeStatementURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching income statement data: " + e.getMessage());
            throw new RuntimeException("Error fetching income statement data.", e);
        }
    }

    @PostMapping("/api/number")
    public ResponseEntity<Map<String, Object>> handleNumber(@RequestBody NumberData numberData) {
        try {
            int number = numberData.getNumber();
            System.out.println("Recieved number: " + number);

            BigDecimal DCF = testDCFwithWAAC();

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Counted DCF for Mastercard: " + DCF);
            response.put("value", DCF);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Invalid number type."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(createErrorResponse("Server error: " + e.getMessage()));
        }
    }

    public static class NumberData {
        private int number;

        public int getNumber() {
            return number;
        }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    public BigDecimal testDCFwithWAAC(){
        // Testing DCFValuationUtil
        DCFValuationUtil dcfUtil = new DCFValuationUtil();

        // Test 1: WAAC calculation for Mastercard (for 2024 data)
        BigDecimal riskFreeRate = new BigDecimal("0.0474"); // 02/14/2025
        BigDecimal beta = new BigDecimal("1.1"); // (5Y Monthly)
        BigDecimal interestExpense = new BigDecimal("646000000");
        BigDecimal totalDebt = new BigDecimal("18226000000");
        BigDecimal marketCapitalization = new BigDecimal("514427000000");
        BigDecimal taxProvision = new BigDecimal("2380000000");
        BigDecimal pretaxIncome = new BigDecimal("15254000000");
        BigDecimal marketRiskPremium = new BigDecimal("0.1"); // Average S&P500 annual return

        try {
            BigDecimal wacc = dcfUtil.calculateWACCFromFinancialAndMarketData(
                    riskFreeRate, beta, interestExpense, totalDebt, marketCapitalization, taxProvision, pretaxIncome, marketRiskPremium
            );

            System.out.println("WACC dla Mastercard: " + wacc);

            // Test 2: Last Year FCF calculation for Mastercard using WAAC as Discount Rate (for 2024 data)
            BigDecimal lastYearFCF = new BigDecimal("13586000000");
            BigDecimal initialGrowthRate = new BigDecimal("0.16"); // Initial high growth
            BigDecimal linkingGrowthRate = new BigDecimal("0.7"); // lining growth
            BigDecimal terminalGrowthRate = new BigDecimal("0.025"); // Sustainable long-term growth
            int highGrowthYears = 3;
            int forecastYears = 10;
            BigDecimal discountRate = wacc;
            long numberOfShares = 911512862;
            BigDecimal netDebt = new BigDecimal("9784000000");

            BigDecimal pricePerShare = dcfUtil.calculateDCF(lastYearFCF, initialGrowthRate, linkingGrowthRate, terminalGrowthRate, highGrowthYears, forecastYears, discountRate, numberOfShares, netDebt);

            System.out.println("Price per share (before margin of safety): " + pricePerShare);
            System.out.println("Price per share (including margin of safety): " + pricePerShare.multiply(new BigDecimal("0.9")));
            return pricePerShare;
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd: " + e.getMessage());
            throw new RuntimeException("Błąd w testDCFwithWAAC: " + e.getMessage(), e);
        }
    }
}