package com.stockapp.StockApp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "stocks", key = "#symbol")
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
    @Cacheable(value = "overview", key = "#symbol")
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
    @Cacheable(value = "incomeStatement", key = "#symbol")
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
    @Cacheable(value = "balanceSheet", key = "#symbol")
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
    @Cacheable(value = "cashFlowStatement", key = "#symbol")
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
    

    @PostMapping("/api/{symbol}/dcfData")
    public ResponseEntity<Map<String, Object>> handleDCFConnection(@RequestBody GrowthRates growthRates, @PathVariable("symbol") String symbol) {
        try {
            
            if (growthRates == null || growthRates.getGrowthRates() == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("DCF data is missing."));
            }
            List<BigDecimal> values = growthRates.getGrowthRates();
            System.out.println("Received DCF data: " + values);

            BigDecimal DCF = testDCFwithWAAC(values, symbol);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Counted DCF for " + symbol + ": " + DCF);
            response.put("value", DCF);

            System.out.println("Price per share: " + DCF);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Invalid number format."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(createErrorResponse("Server error: " + e.getMessage()));
        }
    }

    public static class GrowthRates {
        private List<BigDecimal> GrowthRates;

        public List<BigDecimal> getGrowthRates() {
            return GrowthRates;
        }

        public void setGrowthRates(List<BigDecimal> GrowthRate) {
            this.GrowthRates = GrowthRate;
        }
    }

    public static class statementDataForDCF {
        private Map<String, BigDecimal> statementDataForDCF;

        public Map<String, BigDecimal> getstatementDataForDCF() {
            return statementDataForDCF;
        }

        public void setstatementDataForDCF(Map<String, BigDecimal> statementDataForDCF) {
            this.statementDataForDCF = statementDataForDCF;
        }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }

    public BigDecimal testDCFwithWAAC(List<BigDecimal> growthRates, String symbol){
        // Testing DCFValuationUtil
        DCFValuationUtil dcfUtil = new DCFValuationUtil();


        List<BalanceSheet> balanceSheets = getAnnualBalanceSheet(symbol);
        BalanceSheet latestBalanceSheet = balanceSheets.get(0);               // [Latest BS]
        BigDecimal totalDebt = latestBalanceSheet.getShortLongTermDebtTotal();      // [BS] ---
        BigDecimal netDebt = latestBalanceSheet.getShortLongTermDebtTotal()
                .multiply(BigDecimal.valueOf(0.7));                             // [BS] ---
        
        List<IncomeStatement> incomeStatements = getAnnualIncomeStatements(symbol);
        IncomeStatement latestIncomeStatements = incomeStatements.get(0);     // [Latest IS]
        BigDecimal interestExpense = latestIncomeStatements.getInterestExpense();   // [IS]
        BigDecimal taxProvision = latestIncomeStatements.getIncomeTaxExpense();     // [IS]
        BigDecimal pretaxIncome = latestIncomeStatements.getIncomeBeforeTax();      // [IS] ---
        
        List<CashFlow> cashFlows = getAnnualCashFlow(symbol);
        CashFlow latestCashFlow = cashFlows.get(0);                           // [Latest CFS]
        BigDecimal lastYearFCF = latestCashFlow.getOperatingCashflow()
                .subtract(latestCashFlow.getCapitalExpenditures());                 // [CFS] ---

        Overview overview = getOverview(symbol);                                    // [Overview data]
        BigDecimal beta = overview.getBeta();                                       // [OV]
        BigDecimal marketCapitalization = overview.getMarketCapitalization();       // [OV]
        BigDecimal numberOfShares = overview.getSharesOutstanding();                // [OV]

        System.out.println("totalDebt: " + totalDebt);
        System.out.println("netDebt: " + netDebt);
        System.out.println("interestExpense: " + interestExpense);
        System.out.println("taxProvision: " + taxProvision);
        System.out.println("pretaxIncome: " + pretaxIncome);
        System.out.println("lastYearFCF: " + lastYearFCF);
        System.out.println("beta: " + beta);
        System.out.println("marketCapitalization: " + marketCapitalization);
        System.out.println("numberOfShares: " + numberOfShares);

        BigDecimal riskFreeRate = new BigDecimal("0.0461");     // 02/14/2025 - bonds
        BigDecimal marketRiskPremium = new BigDecimal("0.1");   // Average S&P500 annual return

        
        try {
            // Test 1: WAAC calculation for InPost (for 2024 data)
            BigDecimal wacc = dcfUtil.calculateWACCFromFinancialAndMarketData(
                    riskFreeRate, beta, interestExpense, totalDebt, marketCapitalization, taxProvision, pretaxIncome, marketRiskPremium
            );

            // Test 2: Last Year FCF calculation for InPost using WAAC as Discount Rate (for 2024 data)
            BigDecimal discountRate = wacc;
            BigDecimal pricePerShare = dcfUtil.calculateDCF(lastYearFCF, growthRates, discountRate, numberOfShares, netDebt);

            System.out.println("WACC for " + symbol + ": " + wacc);
            System.out.println("Price per share (before margin of safety): " + pricePerShare);
            return pricePerShare;
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Error in testDCFwithWAAC: " + e.getMessage(), e);
        }
    }
}