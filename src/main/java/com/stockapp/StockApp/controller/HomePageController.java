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
 * REST controller for the home page, providing access to company data.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomePageController {

    /**
     * Retrieves a list of available companies.
     *
     * This endpoint returns a list of Company objects, each containing the 
     * symbol and name of a company.  This data is used to populate
     * the company selection list on the frontend.
     *
     * @return A list of Company objects.
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


        public String getSymbol() { return symbol; }
        public String getName() { return name; }
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