package com.stockapp.StockApp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the home page.
 */
@Controller
public class HomePageController {

    /**
     * Retrieves a list of companies and adds it to the model for display on the home page.
     *
     * @param model The Spring Model object for adding attributes to the view.
     * @return The name of the home page view ("index").
     */
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Company> companies = Arrays.asList(
                new Company("IBM", "International Business Machines"),
                new Company("MSFT", "Microsoft"),
                new Company("GOOG", "Alphabet (Google)")
        );
        model.addAttribute("companies", companies);
        return "index";
    }

    /**
     * Represents a company with its symbol and name.
     */
    public static class Company{
        final private String symbol;
        final private String name;

        /**
         * Constructs a new Company object.
         *
         * @param symbol The company's stock symbol (e.g., IBM, MSFT).
         * @param name   The full name of the company (e.g., International Business Machines, Microsoft).
         */
        public Company(String symbol, String name){
            this.symbol = symbol;
            this.name = name;
        }

        /**
         * Returns the company's stock symbol.
         *
         * @return The company's stock symbol.
         */
        public String getSymbol(){
            return symbol;
        }

        /**
         * Returns the full name of the company.
         *
         * @return The full name of the company.
         */
        public String getName(){
            return name;
        }
    }
}
