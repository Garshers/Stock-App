package com.stockapp.StockApp.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
                jsonData.get("symbol").getAsString(),
                jsonData.get("name").getAsString(),
                jsonData.get("description").getAsString(),
                jsonData.get("exchange").getAsString(),
                jsonData.get("currency").getAsString(),
                jsonData.get("country").getAsString(),
                jsonData.get("sector").getAsString(),
                jsonData.get("industry").getAsString(),
                jsonData.get("fiscalYearEnd").getAsString(),
                jsonData.get("latestQuarter").isJsonNull() ? null : LocalDate.parse(jsonData.get("latestQuarter").getAsString(), dateFormatter),
                jsonData.get("marketCapitalization").isJsonNull() ? null : new BigDecimal(jsonData.get("marketCapitalization").getAsString()),
                jsonData.get("ebitda").isJsonNull() ? null : new BigDecimal(jsonData.get("ebitda").getAsString()),
                jsonData.get("peRatio").isJsonNull() ? null : new BigDecimal(jsonData.get("peRatio").getAsString()),
                jsonData.get("pegRatio").isJsonNull() ? null : new BigDecimal(jsonData.get("pegRatio").getAsString()),
                jsonData.get("bookValue").isJsonNull() ? null : new BigDecimal(jsonData.get("bookValue").getAsString()),
                jsonData.get("dividendPerShare").isJsonNull() ? null : new BigDecimal(jsonData.get("dividendPerShare").getAsString()),
                jsonData.get("dividendYield").isJsonNull() ? null : new BigDecimal(jsonData.get("dividendYield").getAsString()),
                jsonData.get("eps").isJsonNull() ? null : new BigDecimal(jsonData.get("eps").getAsString()),
                jsonData.get("revenuePerShareTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("revenuePerShareTTM").getAsString()),
                jsonData.get("profitMargin").isJsonNull() ? null : new BigDecimal(jsonData.get("profitMargin").getAsString()),
                jsonData.get("operatingMarginTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("operatingMarginTTM").getAsString()),
                jsonData.get("returnOnAssetsTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("returnOnAssetsTTM").getAsString()),
                jsonData.get("returnOnEquityTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("returnOnEquityTTM").getAsString()),
                jsonData.get("revenueTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("revenueTTM").getAsString()),
                jsonData.get("grossProfitTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("grossProfitTTM").getAsString()),
                jsonData.get("dilutedEPSTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("dilutedEPSTTM").getAsString()),
                jsonData.get("quarterlyEarningsGrowthYOY").isJsonNull() ? null : new BigDecimal(jsonData.get("quarterlyEarningsGrowthYOY").getAsString()),
                jsonData.get("quarterlyRevenueGrowthYOY").isJsonNull() ? null : new BigDecimal(jsonData.get("quarterlyRevenueGrowthYOY").getAsString()),
                jsonData.get("analystTargetPrice").isJsonNull() ? null : new BigDecimal(jsonData.get("analystTargetPrice").getAsString()),
                jsonData.get("analystRatingStrongBuy").isJsonNull() ? null : jsonData.get("analystRatingStrongBuy").getAsInt(),
                jsonData.get("analystRatingBuy").isJsonNull() ? null : jsonData.get("analystRatingBuy").getAsInt(),
                jsonData.get("analystRatingHold").isJsonNull() ? null : jsonData.get("analystRatingHold").getAsInt(),
                jsonData.get("analystRatingSell").isJsonNull() ? null : jsonData.get("analystRatingSell").getAsInt(),
                jsonData.get("analystRatingStrongSell").isJsonNull() ? null : jsonData.get("analystRatingStrongSell").getAsInt(),
                jsonData.get("trailingPE").isJsonNull() ? null : new BigDecimal(jsonData.get("trailingPE").getAsString()),
                jsonData.get("forwardPE").isJsonNull() ? null : new BigDecimal(jsonData.get("forwardPE").getAsString()),
                jsonData.get("priceToSalesRatioTTM").isJsonNull() ? null : new BigDecimal(jsonData.get("priceToSalesRatioTTM").getAsString()),
                jsonData.get("priceToBookRatio").isJsonNull() ? null : new BigDecimal(jsonData.get("priceToBookRatio").getAsString()),
                jsonData.get("evToRevenue").isJsonNull() ? null : new BigDecimal(jsonData.get("evToRevenue").getAsString()),
                jsonData.get("evToEBITDA").isJsonNull() ? null : new BigDecimal(jsonData.get("evToEBITDA").getAsString()),
                jsonData.get("beta").isJsonNull() ? null : new BigDecimal(jsonData.get("beta").getAsString()),
                jsonData.get("weekHigh52").isJsonNull() ? null : new BigDecimal(jsonData.get("weekHigh52").getAsString()),
                jsonData.get("weekLow52").isJsonNull() ? null : new BigDecimal(jsonData.get("weekLow52").getAsString()),
                jsonData.get("movingAverage50").isJsonNull() ? null : new BigDecimal(jsonData.get("movingAverage50").getAsString()),
                jsonData.get("movingAverage200").isJsonNull() ? null : new BigDecimal(jsonData.get("movingAverage200").getAsString()),
                jsonData.get("sharesOutstanding").isJsonNull() ? null : jsonData.get("sharesOutstanding").getAsLong(),
                jsonData.get("dividendDate").isJsonNull() ? null : LocalDate.parse(jsonData.get("dividendDate").getAsString(), dateFormatter),
                jsonData.get("exDividendDate").isJsonNull() ? null : LocalDate.parse(jsonData.get("exDividendDate").getAsString(), dateFormatter)
            );

            System.out.println(overview);
            return overview;

        } catch (Exception e) {
            System.err.println("Error prasing overview: " + e.getMessage());
            return null;
        }
    }
}
