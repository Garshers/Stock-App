package com.stockapp.StockApp.model;

public class URLCreator {
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "key"; // Swap this string with your Alpha Vantage API key

    private String symbol;
    private FunctionType function;
    private OutputSize outputSize;

    public URLCreator(String symbol, FunctionType function, OutputSize outputSize) {
        this.symbol = symbol;
        this.function = function;
        this.outputSize = outputSize;
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
        TIME_SERIES_DAILY("TIME_SERIES_DAILY"),
        TIME_SERIES_WEEKLY("TIME_SERIES_WEEKLY"),
        TIME_SERIES_MONTHLY("TIME_SERIES_MONTHLY");

        private final String value;

        FunctionType(String value) {
            this.value = value;
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
