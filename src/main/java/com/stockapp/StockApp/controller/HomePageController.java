package com.stockapp.StockApp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * RestController for the home page.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomePageController {

    /**
     * Retrieves a list of companies and adds it to the model for display on the home page.
     *
     * @param model The Spring Model object for adding attributes to the view.
     * @return The name of the home page view ("index").
     */
    @RequestMapping("/api/companies")
    public List<Company> getHomePage() {
        List<Company> companies = Arrays.asList(
                new Company("IBM", "International Business Machines"),
                new Company("MSFT", "Microsoft"),
                new Company("GOOG", "Alphabet (Google)")
        );
        return companies;
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

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseBody
        public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RuntimeException.class)
        @ResponseBody
        public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
            return new ResponseEntity<>("Error fetching data: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}