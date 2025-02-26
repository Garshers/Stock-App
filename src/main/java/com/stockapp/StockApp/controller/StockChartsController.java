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

import com.stockapp.StockApp.model.BalanceSheet;
import com.stockapp.StockApp.model.CashFlow;
import com.stockapp.StockApp.model.IncomeStatement;
import com.stockapp.StockApp.model.Overview;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;
import com.stockapp.StockApp.util.DCFValuationUtil;

/**
 * REST Controller for the Stock Chart related API endpoints.
 * This controller provides endpoints for retrieving and processing stock market data, 
 * including historical stock prices, financial statements (balance sheets, income statements, 
 * cash flow statements), and other relevant information. 
 * * It is designed to serve data to frontend applications, particularly those visualizing 
 * stock market trends and financial performance.
 * * The controller uses a service layer to handle data retrieval and parsing, ensuring 
 * separation of concerns and maintainability. It also implements cross-origin resource 
 * sharing (CORS) to allow requests from specific origins (e.g., a local React application).
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StockChartsController {
    private final AlphaVantageService service = new AlphaVantageService();

    /**
     * Retrieves stock (price over time) data for a given symbol.
     *
     * @param symbol The stock symbol.
     * @return A list of Stock objects representing the stock data.
     * @throws IllegalArgumentException If the provided symbol is null or empty.
     * @throws RuntimeException If an error occurs during data retrieval.
     */
    @GetMapping("/api/stockDashboard/{symbol}/stocks")
    public List<Stock> getStockData(@PathVariable("symbol") String symbol) {
        if (symbol == null || symbol.isEmpty()){
            throw new IllegalArgumentException("Please provide a stock symbol.");
        }
        URLCreator stockURL = new URLCreator(symbol, URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED);
        String url = stockURL.generateUrl();
        System.out.println("stock url: " + url);

        try {
            String jsonResponse = service.getJSONData(url);
            return service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching stock data: " + e.getMessage());
            throw new RuntimeException("Error fetching stock data.", e);
        }
    }

    /**
     * Retrieves Overview data for a given symbol from an external API endpoint.
     * The data is then parsed and mapped to a Overview object.
     * 
     * @param symbol The stock symbol.
     * @return A Overview object representing overview data.
     * @throws RuntimeException If an error occurs during data retrieval or parsing.
     */
    @GetMapping("/api/stockDashboard/{symbol}/overview")
    public Overview getOverview(@PathVariable("symbol") String symbol) {
        URLCreator URL = new URLCreator(symbol, URLCreator.FunctionType.OVERVIEW);
        String url = URL.generateUrl();
        System.out.println("Overview url: " + url);

        try {
            String jsonResponse = service.getJSONData(url);
            return service.parseOverview(URL.getSymbol(), jsonResponse, URL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching income statement data: " + e.getMessage());
            throw new RuntimeException("Error fetching income statement data.", e);
        }
    }

    /**
     * Retrieves annual income statement data for a given stock symbol from an external API endpoint.
     * The data is then parsed and mapped to a list of IncomeStatement objects.
     *
     * @param symbol The stock symbol.
     * @return A list of Annual IncomeStatement objects representing the annual income statement data.
     * @throws RuntimeException If an error occurs during data retrieval or parsing.
     */
    @GetMapping("/api/stockDashboard/{symbol}/incomeStatement")
    public List<IncomeStatement> getAnnualIncomeStatements(@PathVariable("symbol") String symbol) {
        URLCreator URL = new URLCreator(symbol, URLCreator.FunctionType.INCOME_STATEMENT);
        String url = URL.generateUrl();
        System.out.println("income statement url: " + url);

        try {
            String jsonResponse = service.getJSONData(url);
            return service.parseAnnualIncomeStatement(URL.getSymbol(), jsonResponse, URL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching income statement data: " + e.getMessage());
            throw new RuntimeException("Error fetching income statement data.", e);
        }
    }

    /**
     * Retrieves annual balance sheet data for a given stock symbol from an external API endpoint.
     * The data is then parsed and mapped to a list of BalanceSheet objects.
     *
     * @param symbol The stock symbol.
     * @return A list of Annual BalanceSheet objects representing the annual balance sheet data.
     * @throws RuntimeException If an error occurs during data retrieval or parsing.
     */
    @GetMapping("/api/stockDashboard/{symbol}/balanceSheet")
    public List<BalanceSheet> getAnnualBalanceSheet(@PathVariable("symbol") String symbol) {
        URLCreator URL = new URLCreator(symbol, URLCreator.FunctionType.BALANCE_SHEET);
        String url = URL.generateUrl();
        System.out.println("balance sheet url: " + url);

        try {
            String jsonResponse = service.getJSONData(url);
            return service.parseAnnualBalanceSheet(URL.getSymbol(), jsonResponse, URL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching balance sheet data: " + e.getMessage());
            throw new RuntimeException("Error fetching balance sheet data.", e);
        }
    }

    /**
     * Retrieves annual cash flow statement data for a given stock symbol from an external API endpoint.
     * The data is then parsed and mapped to a list of CashFlow objects.
     *
     * @param symbol The stock symbol.
     * @return A list of Annual CashFlow objects representing the annual balance sheet data.
     * @throws RuntimeException If an error occurs during data retrieval or parsing.
     */
    @GetMapping("/api/stockDashboard/{symbol}/cashFlowStatement")
    public List<CashFlow> getAnnualCashFlow(@PathVariable("symbol") String symbol) {
        URLCreator URL = new URLCreator(symbol, URLCreator.FunctionType.CASH_FLOW);
        String url = URL.generateUrl();
        System.out.println("cash flow url: " + url);

        try {
            String jsonResponse = service.getJSONData(url);
            return service.parseAnnualCashFlow(URL.getSymbol(), jsonResponse, URL.getFunction());
        } catch (Exception e) {
            System.err.println("ERROR fetching cash flow data: " + e.getMessage());
            throw new RuntimeException("Error fetching cash flow data.", e);
        }
    }
    

    @PostMapping("/api/number")
    public ResponseEntity<Map<String, Object>> handleNumber(@RequestBody NumberData numberData) {
        try {
            int number = numberData.getNumber();
            System.out.println("Recieved number: " + number);

            BigDecimal DCF = testDCFwithWAAC();

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Counted DCF for Visa: " + DCF);
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

        public int getNumber() { return number; }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    public BigDecimal testDCFwithWAAC(){
        // Testing DCFValuationUtil
        DCFValuationUtil dcfUtil = new DCFValuationUtil();

        BigDecimal riskFreeRate = new BigDecimal("0.0461");     // 02/14/2025 - bonds
        BigDecimal marketRiskPremium = new BigDecimal("0.1");   // Average S&P500 annual return
        BigDecimal beta = new BigDecimal("0.96");                           // (5Y Monthly) [summary]
        BigDecimal marketCapitalization = new BigDecimal("679368760000");   // [summary]
        long numberOfShares = 1729000000;                                       // [statistics]
        BigDecimal interestExpense = new BigDecimal("636000000");   // [IS]
        BigDecimal taxProvision = new BigDecimal("4102000000");     // [IS]
        BigDecimal pretaxIncome = new BigDecimal("24074000000");    // [IS]
        BigDecimal totalDebt = new BigDecimal("20836000000");       // [BS]
        BigDecimal netDebt = new BigDecimal("8861000000");          // [BS]
        BigDecimal lastYearFCF = new BigDecimal("18693000000");     // [CFS]
        BigDecimal initialGrowthRate = new BigDecimal("0.1292");    // Initial high growth
        BigDecimal linkingGrowthRate = new BigDecimal("0.1");       // lining growth
        BigDecimal terminalGrowthRate = new BigDecimal("0.025");    // Sustainable long-term growth
        int highGrowthYears = 3;
        int forecastYears = 10;
        
        try {
            // Test 1: WAAC calculation for Visa (for 2024 data)
            BigDecimal wacc = dcfUtil.calculateWACCFromFinancialAndMarketData(
                    riskFreeRate, beta, interestExpense, totalDebt, marketCapitalization, taxProvision, pretaxIncome, marketRiskPremium
            );

            // Test 2: Last Year FCF calculation for Visa using WAAC as Discount Rate (for 2024 data)
            BigDecimal discountRate = wacc;
            BigDecimal pricePerShare = dcfUtil.calculateDCF(lastYearFCF, initialGrowthRate, linkingGrowthRate, terminalGrowthRate, highGrowthYears, forecastYears, discountRate, numberOfShares, netDebt);

            System.out.println("WACC dla Visa: " + wacc);
            System.out.println("Price per share (before margin of safety): " + pricePerShare);
            System.out.println("Price per share (including margin of safety): " + pricePerShare.multiply(new BigDecimal("0.9")));
            return pricePerShare;
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Error in testDCFwithWAAC: " + e.getMessage(), e);
        }
    }
}