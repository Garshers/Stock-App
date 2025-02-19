package com.stockapp.StockApp;

import java.math.BigDecimal;

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

    // Test 1: WAAC calculation for Mastercard (for 2024 data)
    BigDecimal riskFreeRate = new BigDecimal("0.0474"); // 02/14/2025
    BigDecimal beta = new BigDecimal("1.1"); // (5Y Monthly)
    BigDecimal interestExpense = new BigDecimal("646000000");
    BigDecimal totalDebt = new BigDecimal("18226000000");
    BigDecimal marketCapitalization = new BigDecimal("514427000000");
    BigDecimal taxProvision = new BigDecimal("2380000000");
    BigDecimal pretaxIncome = new BigDecimal("15254000000");
    BigDecimal marketRiskPremium = new BigDecimal("0.1"); // Average S&P500 annual return

    try {
        BigDecimal wacc = dcfUtil.calculateWACCFromFinancialAndMarketData(
                riskFreeRate, beta, interestExpense, totalDebt, marketCapitalization, taxProvision, pretaxIncome, marketRiskPremium
        );

        System.out.println("WACC dla Mastercard: " + wacc);

        // Test 2: Last Year FCF calculation for Mastercard using WAAC as Discount Rate (for 2024 data)
        BigDecimal lastYearFCF = new BigDecimal("13586000000");
        BigDecimal initialGrowthRate = new BigDecimal("0.16"); // Initial high growth
        BigDecimal linkingGrowthRate = new BigDecimal("0.7"); // Sustainable long-term growth
        BigDecimal terminalGrowthRate = new BigDecimal("0.025"); // Sustainable long-term growth
        int highGrowthYears = 3;
        int forecastYears = 10;
        BigDecimal discountRate = wacc;
        long numberOfShares = 911512862;
        BigDecimal netDebt = new BigDecimal("9784000000");

        BigDecimal pricePerShare = dcfUtil.calculateDCF(lastYearFCF, initialGrowthRate, linkingGrowthRate, terminalGrowthRate, highGrowthYears, forecastYears, discountRate, numberOfShares, netDebt);

        System.out.println("Price per share (before margin of safety): " + pricePerShare);
        System.out.println("Price per share (including margin of safety): " + pricePerShare.multiply(new BigDecimal("0.9")));
    } catch (IllegalArgumentException e) {
        System.err.println("Błąd: " + e.getMessage());
    }
	}
}
