package com.stockapp.StockApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.service.AlphaVantageService;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData("NVDA");
            Stock stock = service.parseStockData("NVDA", jsonResponse);
            System.out.println(stock);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
	}

}
