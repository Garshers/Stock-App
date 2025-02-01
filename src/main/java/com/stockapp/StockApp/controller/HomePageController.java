package com.stockapp.StockApp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Company> companies = Arrays.asList(
                new Company("IBM", "IBM"),
                new Company("MSFT", "Microsoft"),
                new Company("GOOG", "Alphabet")
        );
        model.addAttribute("companies", companies);
        return "index";
    }

    // Klasa Company (lubencja)
    public static class Company{
        final private String symbol;
        final private String name;

        public Company(String symbol, String name){
            this.symbol = symbol;
            this.name = name;
        }

        // Gettery i settery dla symbol i name (konieczne!)
        public String getSymbol(){
            return symbol;
        }
        public String getName(){
            return name;
        }
    }
}
