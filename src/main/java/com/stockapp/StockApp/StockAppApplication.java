package com.stockapp.StockApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.controller.StockChartsController;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
    SpringApplication.run(StockAppApplication.class, args);
    System.out.println("-----Building finnished-----");

    /*BigDecimal gR = new BigDecimal("0.12");
    BigDecimal tGR = new BigDecimal("0.025");
    List<BigDecimal> growthRates = new ArrayList<>();
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(gR);
    growthRates.add(tGR);
    
    StockChartsController controller = new StockChartsController();
    BigDecimal test = controller.testDCFwithWAAC(growthRates);*/
	}
}
