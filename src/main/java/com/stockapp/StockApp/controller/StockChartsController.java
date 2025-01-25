package com.stockapp.StockApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockChartsController {

    @GetMapping("/stockCharts")
    public String getStockChartsPage(Model model) {
        return "stockCharts";
    }
}