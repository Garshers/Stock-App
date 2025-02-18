package com.stockapp.StockApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockapp.StockApp.util.DCFValuationUtil;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
    SpringApplication.run(StockAppApplication.class, args);
    System.out.println("-----Building finnished-----");

    // Testing DCFValuationUtil
    DCFValuationUtil dcfUtil = new DCFValuationUtil();

    // Test 1: FCF List
    List<BigDecimal> freeCashFlows = new ArrayList<>();
    freeCashFlows.add(new BigDecimal("10.0"));
    freeCashFlows.add(new BigDecimal("12.0"));
    freeCashFlows.add(new BigDecimal("14.0"));
    BigDecimal discountRateBigDecimal = new BigDecimal("0.10");
    BigDecimal terminalValueBigDecimal = new BigDecimal("100.0");

    BigDecimal dcfValue1 = dcfUtil.calculateDCF(freeCashFlows, discountRateBigDecimal, terminalValueBigDecimal);
    
    System.out.println("DCF Value (List): " + dcfValue1);

    // Test 2: WAAC calculation
    BigDecimal costOfEquity = new BigDecimal("0.10");
    BigDecimal costOfDebt = new BigDecimal("0.05");
    BigDecimal equityValue = new BigDecimal("1000000");
    BigDecimal debtValue = new BigDecimal("500000");
    BigDecimal taxRate = new BigDecimal("0.25");

    BigDecimal wacc = dcfUtil.calculateWACC(costOfEquity, costOfDebt, equityValue, debtValue, taxRate);

    System.out.println("WACC: " + wacc);

    // Test 3: Last Year FCF, Growth Rate, etc.
    BigDecimal lastYearFCF = new BigDecimal("2284000000");
    BigDecimal initialGrowthRate = new BigDecimal("0.25"); // Initial high growth
    BigDecimal terminalGrowthRate = new BigDecimal("0.15"); // Sustainable long-term growth
    int highGrowthYears = 3;
    int forecastYears = 10;
    BigDecimal discountRate = new BigDecimal("0.26");
    long numberOfShares = 203844410;
    BigDecimal netDebt = new BigDecimal("2001000000");

    BigDecimal pricePerShare = dcfUtil.calculateDCF(lastYearFCF, initialGrowthRate, terminalGrowthRate, highGrowthYears, forecastYears, discountRate, numberOfShares, netDebt);

    System.out.println("Price per share: " + pricePerShare);

	}
}
