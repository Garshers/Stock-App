package com.stockapp.StockApp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
    SpringApplication.run(StockAppApplication.class, args);
    
    URLCreator stockURL = new URLCreator("NVDA",URLCreator.FunctionType.TIME_SERIES_WEEKLY,URLCreator.OutputSize.COMPACT);

    String url = stockURL.generateUrl();
    System.out.println("Generated URL: " + url);
    System.out.println("symbol: " + stockURL.getSymbol());
    System.out.println("function: " + stockURL.getFunction());

		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(url);
            List<Stock> stocks = service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
            System.out.println(stocks);
            for (Stock stock : stocks) {
                System.out.println(stock.getSymbol() + ", " + stock.getPrice() + ", " + stock.getDate());
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
	}
}
