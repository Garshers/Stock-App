package com.stockapp.StockApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stockapp.StockApp.model.AnnualReport;
import com.stockapp.StockApp.model.Stock;
import com.stockapp.StockApp.model.URLCreator;
import com.stockapp.StockApp.service.AlphaVantageService;

/**
 * Controller for the Stock Chart pages.
 */
@Controller
public class StockChartsController {

    /**
     * Retrieves stock data and annual reports for a given symbol and displays them on the stock charts page.
     *
     * @param symbol The stock symbol.
     * @param model  The Spring Model object for adding attributes to the view.
     * @return The name of the stock charts view ("stockCharts") or the error view ("error") if an error occurs.
     */
    @GetMapping("/stockCharts/{symbol}")
    public String getStockChartsPage(@PathVariable String symbol, Model model) {
        if (symbol == null || symbol.isEmpty()){
            model.addAttribute("errorMessage", "Please provide a stock symbol.");
            return "error";
        }
        URLCreator stockURL = new URLCreator(symbol, URLCreator.FunctionType.TIME_SERIES_MONTHLY_ADJUSTED);
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
            System.err.println("ERROR fetching stock data: " + e.getMessage());
            model.addAttribute("errorMessage", "Error fetching stock data. Please try again later.");
            return "error";
        }

        URLCreator stockReportURL = new URLCreator(symbol, URLCreator.FunctionType.INCOME_STATEMENT);
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
            System.err.println("ERROR fetching report data: " + e.getMessage());
            model.addAttribute("errorMessage", "Error fetching report data. Please try again later.");
            return "error";
        }

        return "stockCharts";
    }
}