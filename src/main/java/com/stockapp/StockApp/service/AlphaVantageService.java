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
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockapp.StockApp.model.*;

/**
 * Service class for interacting with the Alpha Vantage API.
 */
public class AlphaVantageService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retrieves data from the specified URL.
     *
     * @param url The URL to fetch data from.
     * @return The JSON response from the URL as a String.
     * @throws Exception If an error occurs during the HTTP request.
     */
    public String getJSONData(String url) throws Exception {
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
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            String timeSeriesFunction = functionType.getJsonFunction();
            JsonNode timeSeries = rootNode.get(timeSeriesFunction);
            if (timeSeries == null) {
                throw new RuntimeException("Not found '" + timeSeriesFunction + "' in JSON answer.");
            }

            List<Stock> stockList = new ArrayList<>();
            timeSeries.fields().forEachRemaining(entry -> {
                String date = entry.getKey();
                JsonNode dailyData = entry.getValue();
                JsonNode closeNode = dailyData.get("5. adjusted close");
                if (closeNode == null) {
                    throw new IllegalArgumentException("Missing '5. adjusted close' for date: " + date);
                }
                double closePrice = closeNode.asDouble();
                stockList.add(new Stock(symbol, closePrice, date));
            });
            Collections.reverse(stockList);
            return stockList;
        } catch (JsonProcessingException | RuntimeException e) {
            throw new RuntimeException("Error parsing stock data: " + e.getMessage(), e);
        }
    }

    /**
     * Parses financial data from a JSON response into a list of objects.
     * This private method is designed to be a universal parser for financial statements, 
     * including Income Statements, Balance Sheets, and Cash Flow statements.
     *
     * @param <T>           The type of objects to be created and returned in the list.
     * @param jsonResponse  The JSON response string to parse.
     * @param functionType  The function type used to extract the JSON function key.
     * @param objectCreator A function that creates an object of type T from a JsonObject.
     * @return              A list of objects of type T, or an empty list if an error occurs or no data is found.
     */
    private <T> List<T> parseFinancialData(String jsonResponse, URLCreator.FunctionType functionType, Function<JsonNode, T> objectCreator) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            String function = functionType.getJsonFunction();
            JsonNode jsonArray = rootNode.get(function);
            List<T> dataList = new ArrayList<>();

            if (jsonArray == null || !jsonArray.isArray()) {
                System.err.println("Error: No data or not array in JSON: " + function);
                return dataList;
            }

            jsonArray.forEach(jsonData -> {
                try {
                    T object = objectCreator.apply(jsonData);
                    if (object != null) {
                        dataList.add(object);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Error creating object: " + e.getMessage());
                }
            });
            return dataList;
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Parses annual Income Statement data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing income statement data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A List of Annual IncomeStatement objects parsed from the JSON response.
     */
    public List<IncomeStatement> parseAnnualIncomeStatement(String symbol, String jsonResponse, URLCreator.FunctionType functionType) {
        return parseFinancialData(jsonResponse, functionType, jsonData -> {
            try {
                return new IncomeStatement(
                        safeGetDate(jsonData, "fiscalDateEnding"),
                        safeGetBigDecimal(jsonData, "grossProfit"),
                        safeGetBigDecimal(jsonData, "totalRevenue"),
                        safeGetBigDecimal(jsonData, "operatingIncome"),
                        safeGetBigDecimal(jsonData, "netIncome"),
                        safeGetBigDecimal(jsonData, "costOfRevenue"),
                        safeGetBigDecimal(jsonData, "costofGoodsAndServicesSold"),
                        safeGetBigDecimal(jsonData, "sellingGeneralAndAdministrative"),
                        safeGetBigDecimal(jsonData, "researchAndDevelopment"),
                        safeGetBigDecimal(jsonData, "operatingExpenses"),
                        safeGetBigDecimal(jsonData, "investmentIncomeNet"),
                        safeGetBigDecimal(jsonData, "netInterestIncome"),
                        safeGetBigDecimal(jsonData, "interestIncome"),
                        safeGetBigDecimal(jsonData, "interestExpense"),
                        safeGetBigDecimal(jsonData, "nonInterestIncome"),
                        safeGetBigDecimal(jsonData, "otherNonOperatingIncome"),
                        safeGetBigDecimal(jsonData, "depreciation"),
                        safeGetBigDecimal(jsonData, "depreciationAndAmortization"),
                        safeGetBigDecimal(jsonData, "incomeBeforeTax"),
                        safeGetBigDecimal(jsonData, "incomeTaxExpense"),
                        safeGetBigDecimal(jsonData, "interestAndDebtExpense"),
                        safeGetBigDecimal(jsonData, "netIncomeFromContinuingOperations"),
                        safeGetBigDecimal(jsonData, "comprehensiveIncomeNetOfTax"),
                        safeGetBigDecimal(jsonData, "ebit"),
                        safeGetBigDecimal(jsonData, "ebitda")
                );
            } catch (Exception e) {
                System.err.println("A general error occurred creating IncomeStatement: " + e.getMessage());
                return null;
            }
        });
    }

    /**
     * Parses annual Balance Sheet data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing balance sheet data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A List of Annual BalanceSheet objects parsed from the JSON response.
     */
    public List<BalanceSheet> parseAnnualBalanceSheet(String symbol, String jsonResponse, URLCreator.FunctionType functionType) {
        return parseFinancialData(jsonResponse, functionType, jsonData -> {
            try {
                return new BalanceSheet(
                    safeGetDate(jsonData, "fiscalDateEnding"),
                    safeGetBigDecimal(jsonData, "totalAssets"),
                    safeGetBigDecimal(jsonData, "totalCurrentAssets"),
                    safeGetBigDecimal(jsonData, "cashAndCashEquivalentsAtCarryingValue"),
                    safeGetBigDecimal(jsonData, "cashAndShortTermInvestments"),
                    safeGetBigDecimal(jsonData, "inventory"),
                    safeGetBigDecimal(jsonData, "currentNetReceivables"),
                    safeGetBigDecimal(jsonData, "totalNonCurrentAssets"),
                    safeGetBigDecimal(jsonData, "propertyPlantEquipment"),
                    safeGetBigDecimal(jsonData, "accumulatedDepreciationAmortizationPPE"),
                    safeGetBigDecimal(jsonData, "intangibleAssets"),
                    safeGetBigDecimal(jsonData, "intangibleAssetsExcludingGoodwill"),
                    safeGetBigDecimal(jsonData, "goodwill"),
                    safeGetBigDecimal(jsonData, "investments"),
                    safeGetBigDecimal(jsonData, "longTermInvestments"),
                    safeGetBigDecimal(jsonData, "shortTermInvestments"),
                    safeGetBigDecimal(jsonData, "otherCurrentAssets"),
                    safeGetBigDecimal(jsonData, "otherNonCurrentAssets"),
                    safeGetBigDecimal(jsonData, "totalLiabilities"),
                    safeGetBigDecimal(jsonData, "totalCurrentLiabilities"),
                    safeGetBigDecimal(jsonData, "currentAccountsPayable"),
                    safeGetBigDecimal(jsonData, "deferredRevenue"),
                    safeGetBigDecimal(jsonData, "currentDebt"),
                    safeGetBigDecimal(jsonData, "shortTermDebt"),
                    safeGetBigDecimal(jsonData, "totalNonCurrentLiabilities"),
                    safeGetBigDecimal(jsonData, "capitalLeaseObligations"),
                    safeGetBigDecimal(jsonData, "longTermDebt"),
                    safeGetBigDecimal(jsonData, "currentLongTermDebt"),
                    safeGetBigDecimal(jsonData, "longTermDebtNoncurrent"),
                    safeGetBigDecimal(jsonData, "shortLongTermDebtTotal"),
                    safeGetBigDecimal(jsonData, "otherCurrentLiabilities"),
                    safeGetBigDecimal(jsonData, "otherNonCurrentLiabilities"),
                    safeGetBigDecimal(jsonData, "totalShareholderEquity"),
                    safeGetBigDecimal(jsonData, "treasuryStock"),
                    safeGetBigDecimal(jsonData, "retainedEarnings"),
                    safeGetBigDecimal(jsonData, "commonStock"),
                    safeGetBigDecimal(jsonData, "commonStockSharesOutstanding")
                );
            } catch (Exception e) {
                System.err.println("A general error occurred creating BalanceSheet: " + e.getMessage());
                return null;
            }
        });
    }

    /**
     * Parses annual Cash Flow data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing cash flow data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A List of Annual CashFlow objects parsed from the JSON response.
     */
    public List<CashFlow> parseAnnualCashFlow(String symbol, String jsonResponse, URLCreator.FunctionType functionType) {
        return parseFinancialData(jsonResponse, functionType, jsonData -> {
            try {
                return new CashFlow(
                        safeGetDate(jsonData, "fiscalDateEnding"),
                        safeGetBigDecimal(jsonData, "operatingCashflow"),
                        safeGetBigDecimal(jsonData, "paymentsForOperatingActivities"),
                        safeGetBigDecimal(jsonData, "proceedsFromOperatingActivities"),
                        safeGetBigDecimal(jsonData, "changeInOperatingLiabilities"),
                        safeGetBigDecimal(jsonData, "changeInOperatingAssets"),
                        safeGetBigDecimal(jsonData, "depreciationDepletionAndAmortization"),
                        safeGetBigDecimal(jsonData, "capitalExpenditures"),
                        safeGetBigDecimal(jsonData, "changeInReceivables"),
                        safeGetBigDecimal(jsonData, "changeInInventory"),
                        safeGetBigDecimal(jsonData, "profitLoss"),
                        safeGetBigDecimal(jsonData, "cashflowFromInvestment"),
                        safeGetBigDecimal(jsonData, "cashflowFromFinancing"),
                        safeGetBigDecimal(jsonData, "proceedsFromRepaymentsOfShortTermDebt"),
                        safeGetBigDecimal(jsonData, "paymentsForRepurchaseOfCommonStock"),
                        safeGetBigDecimal(jsonData, "paymentsForRepurchaseOfEquity"),
                        safeGetBigDecimal(jsonData, "paymentsForRepurchaseOfPreferredStock"),
                        safeGetBigDecimal(jsonData, "dividendPayout"),
                        safeGetBigDecimal(jsonData, "dividendPayoutCommonStock"),
                        safeGetBigDecimal(jsonData, "dividendPayoutPreferredStock"),
                        safeGetBigDecimal(jsonData, "proceedsFromIssuanceOfCommonStock"),
                        safeGetBigDecimal(jsonData, "proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet"),
                        safeGetBigDecimal(jsonData, "proceedsFromIssuanceOfPreferredStock"),
                        safeGetBigDecimal(jsonData, "proceedsFromRepurchaseOfEquity"),
                        safeGetBigDecimal(jsonData, "proceedsFromSaleOfTreasuryStock"),
                        safeGetBigDecimal(jsonData, "changeInCashAndCashEquivalents"),
                        safeGetBigDecimal(jsonData, "changeInExchangeRate"),
                        safeGetBigDecimal(jsonData, "netIncome")
                );
            } catch (Exception e) {
                System.err.println("A general error occurred creating CashFlow: " + e.getMessage());
                return null;
            }
        });
    }

    /**
     * Parses overview data from a JSON response.
     *
     * @param symbol       The stock symbol.
     * @param jsonResponse The JSON response string containing overview data.
     * @param functionType The AlphaVantage API function type used to retrieve the data.
     * @return A Overview object parsed from the JSON response.
     */
    public Overview parseOverview(String symbol, String jsonResponse, URLCreator.FunctionType functionType){
        try {
            JsonNode jsonData = objectMapper.readTree(jsonResponse);
            return new Overview(
                safeGetString(jsonData, "Symbol"),
                safeGetString(jsonData, "Name"),
                safeGetString(jsonData, "Description"),
                safeGetString(jsonData, "Exchange"),
                safeGetString(jsonData, "Currency"),
                safeGetString(jsonData, "Country"),
                safeGetString(jsonData, "Sector"),
                safeGetString(jsonData, "Industry"),
                safeGetString(jsonData, "FiscalYearEnd"),
                safeGetDate(jsonData, "LatestQuarter"),
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
                safeGetBigDecimal(jsonData, "SharesOutstanding"),
                safeGetDate(jsonData, "DividendDate"),
                safeGetDate(jsonData, "ExDividendDate")
            );
        } catch (JsonProcessingException e) {
            System.err.println("Error prasing overview: " + e.getMessage());
            return null;
        }
    }

    // *************************************HELPER FUNCTIONS********************************************

    /**
     * Retrieves a String value from a JSON object, handling null and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The String value or null if the value does not exist, is null, or has an invalid type.
     */
    private String safeGetString(JsonNode json, String key) {
        JsonNode node = json.get(key);
        if (node != null && !node.isNull() && node.isTextual()) {
            return node.asText();
        }
        return null;
    }

    /**
     * Retrieves a BigDecimal value from a JSON object, handling null, "None", and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The BigDecimal value or null if the value does not exist, is null, "None", or has an invalid type.
     */
    private BigDecimal safeGetBigDecimal(JsonNode json, String key) {
        JsonNode node = json.get(key);
        if (node != null && !node.isNull()) {
            if (node.isTextual() && "None".equalsIgnoreCase(node.asText())) {
                return null;
            }
            if (node.isNumber()) {
                return new BigDecimal(node.asText());
            }
            if (node.isTextual()) {
                try {
                    return new BigDecimal(node.asText());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing BigDecimal for key '" + key + "': " + node.asText());
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves a Long value from a JSON object, handling null, "None", and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The Long value or null if the value does not exist, is null, or has an invalid type.
     */
    private Long safeGetLong(JsonNode json, String key) {
        JsonNode node = json.get(key);
        if (node != null && !node.isNull()) {
            if (node.isTextual() && "None".equalsIgnoreCase(node.asText())) {
                return null;
            }
            if (node.isNumber()) {
                return node.asLong();
            }
            if (node.isTextual()) {
                try {
                    return Long.valueOf(node.asText());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing Long for key '" + key + "': " + node.asText());
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves an Integer value from a JSON object, handling null and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The Integer value or null if the value does not exist, is null, or has an invalid type.
     */
    private Integer safeGetInt(JsonNode json, String key) {
        JsonNode node = json.get(key);
        if (node != null && !node.isNull()) {
            if (node.isNumber()) {
                return node.asInt();
            }
            if (node.isTextual()) {
                try {
                    return Integer.valueOf(node.asText());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing Integer for key '" + key + "': " + node.asText());
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves a LocalDate value from a JSON object, handling null and invalid date formats.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The LocalDate value or null if the value does not exist, is null, or has an invalid format.
     */
    private LocalDate safeGetDate(JsonNode json, String key) {
        JsonNode node = json.get(key);
        if (node != null && !node.isNull() && node.isTextual()) {
            String dateString = node.asText();
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date for key '" + key + "': " + dateString + ". Expected format: yyyy-MM-dd");
                return null;
            }
        }
        return null;
    }
}
