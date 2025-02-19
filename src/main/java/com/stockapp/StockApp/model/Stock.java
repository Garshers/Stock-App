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


    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public String getDate() { return date; }
    
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