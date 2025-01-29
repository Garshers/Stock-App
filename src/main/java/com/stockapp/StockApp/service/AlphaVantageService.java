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
import com.stockapp.StockApp.model.AnnualReport;
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
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
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
     * @param symbol
     * @param jsonResponse
     * @param functionType
     * @return
     */
    public List<AnnualReport> parseAnnualReports(String symbol, String jsonResponse, URLCreator.FunctionType functionType){
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String Function = functionType.getJsonFunction();
        JsonArray annualReportsJsonArray = jsonObject.getAsJsonArray(Function); 
        List<AnnualReport> annualReportsList = new ArrayList<>();
        for (int i = 0; i < annualReportsJsonArray.size(); i++) {
            JsonObject reportJson = annualReportsJsonArray.get(i).getAsJsonObject();
            
            AnnualReport annualReport = new AnnualReport(
                    reportJson.get("fiscalDateEnding").getAsString(),
                    reportJson.get("reportedCurrency").getAsString(),
                    reportJson.get("grossProfit").getAsString(),
                    reportJson.get("totalRevenue").getAsString(),
                    reportJson.get("operatingIncome").getAsString(),
                    reportJson.get("netIncome").getAsString()
            );
            
            annualReportsList.add(annualReport);
        }
        //System.out.println(annualReportsList); //Log
        
        return annualReportsList;
    }
}
