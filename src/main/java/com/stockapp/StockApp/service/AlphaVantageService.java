package com.stockapp.StockApp.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stockapp.StockApp.model.IncomeStatement;
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
            
            IncomeStatement annualIncomeStatement = new IncomeStatement(
                    jsonData.get("fiscalDateEnding").getAsString(),
                    jsonData.get("reportedCurrency").getAsString(),
                    jsonData.get("grossProfit").getAsLong(),
                    jsonData.get("totalRevenue").getAsLong(),
                    jsonData.get("operatingIncome").getAsLong(),
                    jsonData.get("netIncome").getAsLong()
            );
            
            annualIncomeStatementList.add(annualIncomeStatement);
        }
        
        return annualIncomeStatementList;
    }
}
