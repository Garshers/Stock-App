package com.stockapp.StockApp.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.stockapp.StockApp.model.IncomeStatement;
import com.stockapp.StockApp.model.Overview;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;

/**
 * Service class for interacting with the Alpha Vantage API.
 */
public class AlphaVantageService {

    /**
     * Retrieves data from the specified URL.
     *
     * @param url The URL to fetch data from.
     * @return The JSON response from the URL as a String.
     * @throws Exception If an error occurs during the HTTP request.
     */
    public String getStockData(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error in API: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Parses stock data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A list of Stock objects parsed from the JSON response.
     * @throws RuntimeException If the specified time series function is not found in the JSON response.
     * @throws IllegalArgumentException If the "5. adjusted close" value is missing for a date.
     */
    public List<Stock> parseStockData(String symbol, String jsonResponse, URLCreator.FunctionType functionType) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String timeSeriesFunction = functionType.getJsonFunction();
        JsonObject timeSeries = jsonObject.getAsJsonObject(timeSeriesFunction);
        if (timeSeries == null) {
            throw new RuntimeException("Not found '" + timeSeriesFunction + "' in JSON answer.");
        }

        List<Stock> stockList = new ArrayList<>();
        for (String date : timeSeries.keySet()) {
            JsonObject dailyData = timeSeries.getAsJsonObject(date);
            if (!dailyData.has("5. adjusted close")) {
                throw new IllegalArgumentException("Missing '5. adjusted close' for date: " + date);
            }
            double closePrice = dailyData.get("5. adjusted close").getAsDouble();
            stockList.add(new Stock(symbol, closePrice, date));
        }
        Collections.reverse(stockList);

        return stockList;
    }

    /**
     * Parses annual income statemens data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing annual income statement data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A list of AnnualIncomeStatement objects parsed from the JSON response.
     */
    public List<IncomeStatement> parseAnnualIncomeStatement(String symbol, String jsonResponse, URLCreator.FunctionType functionType){
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String Function = functionType.getJsonFunction();
        JsonArray annualIncomeStatementJsonArray = jsonObject.getAsJsonArray(Function); 
        List<IncomeStatement> annualIncomeStatementList = new ArrayList<>();
        
        for (int i = 0; i < annualIncomeStatementJsonArray.size(); i++) {
            JsonObject jsonData = annualIncomeStatementJsonArray.get(i).getAsJsonObject();
            
            try {
                IncomeStatement annualIncomeStatement = new IncomeStatement(
                    safeGetString(jsonData, "fiscalDateEnding"),
                    safeGetLong(jsonData, "grossProfit"),
                    safeGetLong(jsonData, "totalRevenue"),
                    safeGetLong(jsonData, "operatingIncome"),
                    safeGetLong(jsonData, "netIncome"),
                    safeGetLong(jsonData, "costOfRevenue"),
                    safeGetLong(jsonData, "costofGoodsAndServicesSold"),
                    safeGetLong(jsonData, "sellingGeneralAndAdministrative"),
                    safeGetLong(jsonData, "researchAndDevelopment"),
                    safeGetLong(jsonData, "operatingExpenses"),
                    safeGetLong(jsonData, "investmentIncomeNet"),
                    safeGetLong(jsonData, "netInterestIncome"),
                    safeGetLong(jsonData, "interestIncome"),
                    safeGetLong(jsonData, "interestExpense"),
                    safeGetLong(jsonData, "nonInterestIncome"),
                    safeGetLong(jsonData, "otherNonOperatingIncome"),
                    safeGetLong(jsonData, "depreciation"),
                    safeGetLong(jsonData, "depreciationAndAmortization"),
                    safeGetLong(jsonData, "incomeBeforeTax"),
                    safeGetLong(jsonData, "incomeTaxExpense"),
                    safeGetLong(jsonData, "interestAndDebtExpense"),
                    safeGetLong(jsonData, "netIncomeFromContinuingOperations"),
                    safeGetLong(jsonData, "comprehensiveIncomeNetOfTax"),
                    safeGetLong(jsonData, "ebit"),
                    safeGetLong(jsonData, "ebitda")
                );
            
                annualIncomeStatementList.add(annualIncomeStatement);
            
            } catch (JsonParseException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Error creating IncomeStatement: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("A general error occurred: " + e.getMessage());
            }
            
            
        }
        
        System.out.println(annualIncomeStatementList + " From Service"); // Log
        return annualIncomeStatementList;
    }

    /**
     * Parses overview data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing overview data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A list of Overview objects parsed from the JSON response.
     */
    public Overview parseOverview(String symbol, String jsonResponse, URLCreator.FunctionType functionType){
        JsonObject jsonData = JsonParser.parseString(jsonResponse).getAsJsonObject();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            Overview overview = new Overview(
                safeGetString(jsonData, "Symbol"),
                safeGetString(jsonData, "Name"),
                safeGetString(jsonData, "Description"),
                safeGetString(jsonData, "Exchange"),
                safeGetString(jsonData, "Currency"),
                safeGetString(jsonData, "Country"),
                safeGetString(jsonData, "Sector"),
                safeGetString(jsonData, "Industry"),
                safeGetString(jsonData, "FiscalYearEnd"),
                safeGetDate(jsonData, "LatestQuarter", dateFormatter),
                safeGetBigDecimal(jsonData, "MarketCapitalization"),
                safeGetBigDecimal(jsonData, "EBITDA"),
                safeGetBigDecimal(jsonData, "PERatio"),
                safeGetBigDecimal(jsonData, "PEGRatio"),
                safeGetBigDecimal(jsonData, "BookValue"),
                safeGetBigDecimal(jsonData, "DividendPerShare"),
                safeGetBigDecimal(jsonData, "DividendYield"),
                safeGetBigDecimal(jsonData, "EPS"),
                safeGetBigDecimal(jsonData, "RevenuePerShareTTM"),
                safeGetBigDecimal(jsonData, "ProfitMargin"),
                safeGetBigDecimal(jsonData, "OperatingMarginTTM"),
                safeGetBigDecimal(jsonData, "ReturnOnAssetsTTM"),
                safeGetBigDecimal(jsonData, "ReturnOnEquityTTM"),
                safeGetBigDecimal(jsonData, "RevenueTTM"),
                safeGetBigDecimal(jsonData, "GrossProfitTTM"),
                safeGetBigDecimal(jsonData, "DilutedEPSTTM"),
                safeGetBigDecimal(jsonData, "QuarterlyEarningsGrowthYOY"),
                safeGetBigDecimal(jsonData, "QuarterlyRevenueGrowthYOY"),
                safeGetBigDecimal(jsonData, "AnalystTargetPrice"),
                safeGetInt(jsonData, "AnalystRatingStrongBuy"),
                safeGetInt(jsonData, "AnalystRatingBuy"),
                safeGetInt(jsonData, "AnalystRatingHold"),
                safeGetInt(jsonData, "AnalystRatingSell"),
                safeGetInt(jsonData, "AnalystRatingStrongSell"),
                safeGetBigDecimal(jsonData, "TrailingPE"),
                safeGetBigDecimal(jsonData, "ForwardPE"),
                safeGetBigDecimal(jsonData, "PriceToSalesRatioTTM"),
                safeGetBigDecimal(jsonData, "PriceToBookRatio"),
                safeGetBigDecimal(jsonData, "EVToRevenue"),
                safeGetBigDecimal(jsonData, "EVToEBITDA"),
                safeGetBigDecimal(jsonData, "Beta"),
                safeGetBigDecimal(jsonData, "52WeekHigh"),
                safeGetBigDecimal(jsonData, "52WeekLow"),
                safeGetBigDecimal(jsonData, "50DayMovingAverage"),
                safeGetBigDecimal(jsonData, "200DayMovingAverage"),
                safeGetLong(jsonData, "SharesOutstanding"),
                safeGetDate(jsonData, "DividendDate", dateFormatter),
                safeGetDate(jsonData, "ExDividendDate", dateFormatter)
            );

            return overview;

        } catch (Exception e) {
            System.err.println("Error prasing overview: " + e.getMessage());
            return null;
        }
    }

    // ***HELPER FUNCTIONS***
    private String safeGetString(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            return element != null && !element.isJsonNull() ? element.getAsString() : null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected String.");
            return null;
        }
    }

    private BigDecimal safeGetBigDecimal(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String value = element.getAsString();
                try {
                return new BigDecimal(value);
                } catch (NumberFormatException ex) {
                System.err.println("Error parsing BigDecimal for key '" + key + "': " + value);
                return null;
                }
            }
            return null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected BigDecimal.");
            return null;
        }
    }

    private Long safeGetLong(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String value = element.getAsString();
                if (!"None".equalsIgnoreCase(value)) {
                    try {
                        return Long.valueOf(value);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing Long for key '" + key + "': " + value);
                        return null;
                    }
                } else {
                    return null;
                }
            }
            return null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected Long.");
            return null;
        }
    }

    private Integer safeGetInt(JsonObject json, String key) {
    try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String value = element.getAsString();
                try {
                    return Integer.valueOf(value);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing Integer for key '" + key + "': " + value);
                    return null;
                }
            }
            return null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected Integer.");
            return null;
        }
    }

    private LocalDate safeGetDate(JsonObject json, String key, DateTimeFormatter formatter) {
        try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String dateString = element.getAsString();
                try {
                    return LocalDate.parse(dateString, formatter);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date for key '" + key + "': " + dateString + ".  Expected format: " + formatter.format(LocalDate.now()));
                    return null;
                }
            }
            return null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected Date String.");
            return null;
        }
    }
}
