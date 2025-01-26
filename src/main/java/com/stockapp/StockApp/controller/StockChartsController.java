package com.stockapp.StockApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

@Controller
public class StockChartsController {

    @GetMapping("/stockCharts")
    public String getStockChartsPage(Model model){
        URLCreator stockURL = new URLCreator("NVDA",URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED,URLCreator.OutputSize.COMPACT);

        String url = stockURL.generateUrl();
        System.out.println("Generated URL: " + url);
        System.out.println("symbol: " + stockURL.getSymbol());
        System.out.println("function: " + stockURL.getFunction());

		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(url);
            List<Stock> stocks = service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
            model.addAttribute("stocks", stocks);
            for(Stock stock : stocks){
                System.out.println(stock);
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        return "stockCharts";
    }
}