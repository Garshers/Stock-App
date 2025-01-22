package com.stockapp.StockApp.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stockapp.StockApp.model.Stock;

public class AlphaVantageService {
    private static final String API_KEY = "KEY"; // Swap this string with your Alpha Vantage API key

    /**
     * @param symbol
     * @return JSON answer AV API.
     * @throws Exception
     */
    public String getStockData(String symbol) throws Exception {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + API_KEY;

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
     * @return
     */
    public Stock parseStockData(String symbol, String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject timeSeries = jsonObject.getAsJsonObject("Time Series (Daily)");
        if (timeSeries == null) {
            throw new RuntimeException("Not found 'Time Series (Daily)' in JSON answer.");
        }
        String latestDate = timeSeries.keySet().iterator().next();
        JsonObject latestData = timeSeries.getAsJsonObject(latestDate);

        double closePrice = latestData.get("4. close").getAsDouble();

        return new Stock(symbol, closePrice, latestDate);
    }
}
