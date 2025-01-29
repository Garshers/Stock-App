package com.stockapp.StockApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stockapp.StockApp.model.AnnualReport;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

@Controller
public class StockChartsController {

    @GetMapping("/stockCharts")
    public String getStockChartsPage(Model model){
        URLCreator stockURL = new URLCreator("IBM",URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED);//,URLCreator.OutputSize.COMPACT);
        String url = stockURL.generateUrl();
        System.out.println("Generated URL: " + url); //Log
        System.out.println("symbol: " + stockURL.getSymbol()); //Log
        System.out.println("function: " + stockURL.getFunction() + "\n"); //Log

		AlphaVantageService service = new AlphaVantageService();
        try {
            String jsonResponse = service.getStockData(url);
            //System.out.println(jsonResponse); //Log
            List<Stock> stocks = service.parseStockData(stockURL.getSymbol(), jsonResponse, stockURL.getFunction());
            model.addAttribute("stocks", stocks);
            //System.out.println("------'stocks' was sent------"); //Log
            /*for(Stock stock : stocks){
                System.out.println(stock);
            }*/
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        URLCreator stockReportURL = new URLCreator("IBM",URLCreator.FunctionType.INCOME_STATEMENT);
        String reportUrl = stockReportURL.generateUrl();
        System.out.println("Generated URL: " + reportUrl); //Log
        System.out.println("symbol: " + stockReportURL.getSymbol()); //Log
        System.out.println("function: " + stockReportURL.getFunction()); //Log

        AlphaVantageService serviceReport = new AlphaVantageService();
        try {
            String jsonResponse = serviceReport.getStockData(reportUrl);
            //System.out.println(jsonResponse); //Log
            List<AnnualReport> annualReports = service.parseAnnualReports(stockReportURL.getSymbol(), jsonResponse, stockReportURL.getFunction());
            model.addAttribute("annualReports", annualReports);
            //System.out.println("------'annualReport' was sent------" + annualReports); //Log
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        
        return "stockCharts";
    }
}