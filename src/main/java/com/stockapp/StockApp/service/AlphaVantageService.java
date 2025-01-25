package com.stockapp.StockApp.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;

public class AlphaVantageService {
    /**
     * @param symbol
     * @return JSON answer AV API.
     * @throws Exception
     */
    public String getStockData(String url) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error in API: " + response.statusCode());
        }

        return response.body();
    }

    /**
     * @param symbol
     * @param jsonResponse
     * @param functionType
     * @return
     */
    public Stock parseStockData(String symbol, String jsonResponse, URLCreator.FunctionType functionType) {
        String timeSeriesKey;
        switch (functionType) {
            case TIME_SERIES_DAILY:
                timeSeriesKey = "Time Series (Daily)";
                break;
            case TIME_SERIES_WEEKLY:
                timeSeriesKey = "Weekly Time Series";
                break;
            case TIME_SERIES_MONTHLY:
                timeSeriesKey = "Monthly Time Series";
                break;
            default:
                throw new IllegalArgumentException("Unsupported FunctionType: " + functionType);
        }

        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject timeSeries = jsonObject.getAsJsonObject(timeSeriesKey);
        if (timeSeries == null) {
            throw new RuntimeException("Not found '" + timeSeriesKey + "' in JSON answer.");
        }

        String latestDate = timeSeries.keySet().iterator().next();
        JsonObject latestData = timeSeries.getAsJsonObject(latestDate);

        double closePrice = latestData.get("4. close").getAsDouble();

        return new Stock(symbol, closePrice, latestDate);
    }
}
