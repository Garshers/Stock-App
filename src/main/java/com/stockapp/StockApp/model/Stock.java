package com.stockapp.StockApp.model;

/**
 * Represents a stock with its symbol, price, and date.
 */
public class Stock {
    final private String symbol;
    final private double price;
    final private String date;

    /**
     * Constructs a new Stock object.
     *
     * @param symbol The stock symbol (e.g., AAPL, MSFT).
     * @param price  The current price of the stock.
     * @param date   The date of the price information (e.g., YYYY-MM-DD).
     */
    public Stock(String symbol, double price, String date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    /**
     * Returns the stock symbol.
     *
     * @return The stock symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the current price of the stock.
     *
     * @return The stock price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the date of the price information.
     *
     * @return The date of the price.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns a string representation of the Stock object.
     *
     * @return A string representation of the Stock object.
     */
    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}