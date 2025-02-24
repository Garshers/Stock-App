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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.stockapp.StockApp.model.BalanceSheet;
import com.stockapp.StockApp.model.CashFlow;
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
    private <T> List<T> parseFinancialData(String jsonResponse, URLCreator.FunctionType functionType, Function<JsonObject, T> objectCreator) {
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            String function = functionType.getJsonFunction();
            JsonArray jsonArray = jsonObject.getAsJsonArray(function);
            List<T> dataList = new ArrayList<>();

            if (jsonArray == null) {
                System.err.println("Error: No data in JSON: " + function);
                return dataList;
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonData = jsonArray.get(i).getAsJsonObject();
                try {
                    T object = objectCreator.apply(jsonData);
                    if (object != null) {
                        dataList.add(object);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Error creating object: " + e.getMessage());
                }
            }
            return dataList;
        } catch (JsonParseException e) {
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
        JsonObject jsonData = JsonParser.parseString(jsonResponse).getAsJsonObject();

        try {
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
                safeGetLong(jsonData, "SharesOutstanding"),
                safeGetDate(jsonData, "DividendDate"),
                safeGetDate(jsonData, "ExDividendDate")
            );
        } catch (Exception e) {
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
    private String safeGetString(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            return element != null && !element.isJsonNull() ? element.getAsString() : null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected String.");
            return null;
        }
    }

    /**
     * Retrieves a BigDecimal value from a JSON object, handling null, "None", and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The BigDecimal value or null if the value does not exist, is null, "None", or has an invalid type.
     */
    private BigDecimal safeGetBigDecimal(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String value = element.getAsString();
                if ("None".equalsIgnoreCase(value)) {
                    return null; // JSON can be "None" for some values
                }
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

    /**
     * Retrieves a Long value from a JSON object, handling null, "None", and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The Long value or null if the value does not exist, is null, or has an invalid type.
     */
    private Long safeGetLong(JsonObject json, String key) {
        try {
            JsonElement element = json.get(key);
            if (element != null && !element.isJsonNull()) {
                String value = element.getAsString();
                if ("None".equalsIgnoreCase(value)) {
                    return null;
                }
                try {
                    return Long.valueOf(value);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing Long for key '" + key + "': " + value);
                    return null;
                }
            }
            return null;
        } catch (ClassCastException e) {
            System.err.println("Error: Invalid type for key '" + key + "'. Expected Long.");
            return null;
        }
    }

    /**
     * Retrieves an Integer value from a JSON object, handling null and invalid types.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The Integer value or null if the value does not exist, is null, or has an invalid type.
     */
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

    /**
     * Retrieves a LocalDate value from a JSON object, handling null and invalid date formats.
     *
     * @param json The JSON object to retrieve the value from.
     * @param key  The key under which the value is located in the JSON.
     * @return The LocalDate value or null if the value does not exist, is null, or has an invalid format.
     */
    private LocalDate safeGetDate(JsonObject json, String key) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
