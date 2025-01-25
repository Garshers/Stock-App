package com.stockapp.StockApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
        URLCreator stockURL = new URLCreator(
            "NVDA",
            URLCreator.FunctionType.TIME_SERIES_WEEKLY,
            URLCreator.OutputSize.COMPACT
    );

    String url = stockURL.generateUrl();
    System.out.println("Generated URL: " + url);
    System.out.println("symbol: " + stockURL.getSymbol());
    System.out.println("symbol: " + stockURL.getFunction());

		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(url);
            Stock stock = service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
            System.out.println(stock);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
	}

}
