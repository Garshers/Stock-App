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

    // Test 2: WAAC calculation for Mastercard (2024 data)
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
    } catch (IllegalArgumentException e) {
        System.err.println("Błąd: " + e.getMessage());
    }

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
