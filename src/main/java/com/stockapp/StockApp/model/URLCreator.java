package com.stockapp.StockApp.model;

public class URLCreator {
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "demo"; // Swap this string with your Alpha Vantage API key

    private String symbol;
    private FunctionType function;
    private OutputSize outputSize;

    public URLCreator(String symbol, FunctionType function, OutputSize outputSize) {
        this.symbol = symbol;
        this.function = function;
        this.outputSize = outputSize;
    }

    public URLCreator(String symbol, FunctionType function) {
        this.symbol = symbol;
        this.function = function;
        this.outputSize = null;
    }

    public String generateUrl() {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?function=").append(function);
        urlBuilder.append("&symbol=").append(symbol);
        urlBuilder.append("&apikey=").append(API_KEY);

        if (outputSize != null) {
            urlBuilder.append("&outputsize=").append(outputSize);
        }

        return urlBuilder.toString();
    }

    public String getSymbol() {
        return symbol;
    }
    public FunctionType getFunction() {
        return function;
    }

    public enum FunctionType {
        TIME_SERIES_DAILY_ADJUSTED("Daily Adjusted Time Series"),
        TIME_SERIES_WEEKLY_ADJUSTED("Weekly Adjusted Time Series"),
        TIME_SERIES_MONTHLY_ADJUSTED("Monthly Adjusted Time Series"),
        OVERVIEW("Description"),
        INCOME_STATEMENT("annualReports");
    
        private final String jsonFunction;
    
        FunctionType(String jsonFunction) {
            this.jsonFunction = jsonFunction;
        }
    
        public String getJsonFunction() {
            return jsonFunction;
        }
    }

    public enum OutputSize {
        FULL("full"),
        COMPACT("compact");

        private final String value;

        OutputSize(String value) {
            this.value = value;
        }
    }
}
