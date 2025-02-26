package com.stockapp.StockApp;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.controller.StockChartsController;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
    SpringApplication.run(StockAppApplication.class, args);
    System.out.println("-----Building finnished-----");

    StockChartsController controller = new StockChartsController();
    BigDecimal test = controller.testDCFwithWAAC();
	}
}
