package com.stockapp.StockApp.model;

public class Stock {
    private String symbol;
    private double price;
    private String date;

    public Stock(String symbol, double price, String date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}