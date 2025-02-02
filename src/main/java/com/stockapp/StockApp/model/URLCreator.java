package com.stockapp.StockApp.model;

/**
 * The {@code URLCreator} class is responsible for generating URLs for the Alpha Vantage API.
 * It constructs the URL based on the provided stock symbol, function type, and output size.
 * <p>
 * This class uses the builder pattern for constructing the URL, allowing for flexibility
 * in specifying the parameters.  It also encapsulates the API key, though in this example
 * it is a demo key and should be replaced in a production environment.
 */
public class URLCreator {
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "demo"; // **REPLACE WITH YOUR API KEY**

    final private String symbol;
    final private FunctionType function;
    final private OutputSize outputSize;

    /**
     * Constructs a new {@code URLCreator} with the specified stock symbol, function type, and output size.
     *
     * @param symbol     The stock symbol (e.g., "AAPL", "MSFT").
     * @param function   The function type to be used in the API call.
     * @param outputSize The output size of the API response (e.g., FULL, COMPACT).  Can be {@code null}.
     */
    public URLCreator(String symbol, FunctionType function, OutputSize outputSize) {
        this.symbol = symbol;
        this.function = function;
        this.outputSize = outputSize;
    }

    /**
     * Constructs a new {@code URLCreator} with the specified stock symbol and function type.
     * The output size is set to {@code null}.
     *
     * @param symbol   The stock symbol (e.g., "AAPL", "MSFT").
     * @param function The function type to be used in the API call.
     */
    public URLCreator(String symbol, FunctionType function) {
        this.symbol = symbol;
        this.function = function;
        this.outputSize = null;
    }

    /**
     * Generates the URL for the Alpha Vantage API.
     *
     * @return The generated URL as a {@code String}.
     */
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

    /**
     * Returns the stock symbol.
     *
     * @return The stock symbol as a {@code String}.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the function type.
     *
     * @return The function type as a {@code FunctionType}.
     */
    public FunctionType getFunction() {
        return function;
    }

    /**
     * Enum representing the different function types available in the Alpha Vantage API.
     */
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
    
        /**
         * Returns the function name as expected by the AlphaVantage API.
         * @return the name of the function that the API expects.
         */
        public String getJsonFunction() {
            return jsonFunction;
        }
    }

    /**
     * Enum representing the different output sizes available in the Alpha Vantage API.
     */
    public enum OutputSize {
        FULL("full"),
        COMPACT("compact");

        private final String value;

        OutputSize(String value) {
            this.value = value;
        }

        /**
         * Returns the output size value as expected by the AlphaVantage API.
         * @return the value of the output size as a String.
         */
        public String getOutput() {
            return value;
        }
    }
}
