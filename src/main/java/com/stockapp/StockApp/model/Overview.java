package com.stockapp.StockApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The {@code Overview} class represents stock overview data retrieved from an API such as AlphaVantage.
 * It encapsulates detailed fundamental analysis information about a company.
 */
public class Overview {
    private final String symbol;
    private final String name;
    private final String description;
    private final String exchange;
    private final String currency;
    private final String country;
    private final String sector;
    private final String industry;
    private final String fiscalYearEnd;
    private final LocalDate latestQuarter;
    private final BigDecimal marketCapitalization;
    private final BigDecimal ebitda;
    private final BigDecimal peRatio;
    private final BigDecimal pegRatio;
    private final BigDecimal bookValue;
    private final BigDecimal dividendPerShare;
    private final BigDecimal dividendYield;
    private final BigDecimal eps;
    private final BigDecimal revenuePerShareTTM;
    private final BigDecimal profitMargin;
    private final BigDecimal operatingMarginTTM;
    private final BigDecimal returnOnAssetsTTM;
    private final BigDecimal returnOnEquityTTM;
    private final BigDecimal revenueTTM;
    private final BigDecimal grossProfitTTM;
    private final BigDecimal dilutedEPSTTM;
    private final BigDecimal quarterlyEarningsGrowthYOY;
    private final BigDecimal quarterlyRevenueGrowthYOY;
    private final BigDecimal analystTargetPrice;
    private final Integer analystRatingStrongBuy;
    private final Integer analystRatingBuy;
    private final Integer analystRatingHold;
    private final Integer analystRatingSell;
    private final Integer analystRatingStrongSell;
    private final BigDecimal trailingPE;
    private final BigDecimal forwardPE;
    private final BigDecimal priceToSalesRatioTTM;
    private final BigDecimal priceToBookRatio;
    private final BigDecimal evToRevenue;
    private final BigDecimal evToEBITDA;
    private final BigDecimal beta;
    private final BigDecimal weekHigh52;
    private final BigDecimal weekLow52;
    private final BigDecimal movingAverage50;
    private final BigDecimal movingAverage200;
    private final Long sharesOutstanding;
    private final LocalDate dividendDate;
    private final LocalDate exDividendDate;

    /**
     * Constructs an {@code Overview} object.
     *
     * @param symbol                     The stock symbol.
     * @param name                       The company name.
     * @param description                The company description.
     * @param exchange                   The stock exchange where the company is listed.
     * @param currency                   The currency in which financial data is reported.
     * @param country                    The country where the company operates.
     * @param sector                     The sector the company belongs to.
     * @param industry                   The industry the company operates in.
     * @param fiscalYearEnd              The end date of the company's fiscal year.
     * @param latestQuarter              The date of the latest reported quarter.
     * @param marketCapitalization       The market capitalization of the company.
     * @param ebitda                     The earnings before interest, taxes, depreciation, and amortization.
     * @param peRatio                    The price-to-earnings ratio.
     * @param pegRatio                   The price-to-earnings growth ratio.
     * @param bookValue                  The book value of the company.
     * @param dividendPerShare           The dividend per share.
     * @param dividendYield              The dividend yield.
     * @param eps                        The earnings per share.
     * @param revenuePerShareTTM         The revenue per share (Trailing Twelve Months).
     * @param profitMargin               The profit margin.
     * @param operatingMarginTTM         The operating margin (Trailing Twelve Months).
     * @param returnOnAssetsTTM          The return on assets (Trailing Twelve Months).
     * @param returnOnEquityTTM          The return on equity (Trailing Twelve Months).
     * @param revenueTTM                 The revenue (Trailing Twelve Months).
     * @param grossProfitTTM             The gross profit (Trailing Twelve Months).
     * @param dilutedEPSTTM              The diluted earnings per share (Trailing Twelve Months).
     * @param quarterlyEarningsGrowthYOY  The quarterly earnings growth year-over-year.
     * @param quarterlyRevenueGrowthYOY The quarterly revenue growth year-over-year.
     * @param analystTargetPrice         The analyst target price.
     * @param analystRatingStrongBuy     The number of "Strong Buy" analyst ratings.
     * @param analystRatingBuy           The number of "Buy" analyst ratings.
     * @param analystRatingHold          The number of "Hold" analyst ratings.
     * @param analystRatingSell          The number of "Sell" analyst ratings.
     * @param analystRatingStrongSell     The number of "Strong Sell" analyst ratings.
     * @param trailingPE                 The trailing price-to-earnings ratio.
     * @param forwardPE                  The forward price-to-earnings ratio.
     * @param priceToSalesRatioTTM       The price-to-sales ratio (Trailing Twelve Months).
     * @param priceToBookRatio           The price-to-book ratio.
     * @param evToRevenue                The enterprise value to revenue ratio.
     * @param evToEBITDA                 The enterprise value to EBITDA ratio.
     * @param beta                       The beta.
     * @param weekHigh52                 The 52-week high.
     * @param weekLow52                  The 52-week low.
     * @param movingAverage50            The 50-day moving average.
     * @param movingAverage200           The 200-day moving average.
     * @param sharesOutstanding          The number of shares outstanding.
     * @param dividendDate               The dividend date.
     * @param exDividendDate            The ex-dividend date.
     */
    public Overview(String symbol, String name, String description, String exchange, String currency, String country, String sector, String industry, String fiscalYearEnd, LocalDate latestQuarter, BigDecimal marketCapitalization, BigDecimal ebitda, BigDecimal peRatio, BigDecimal pegRatio, BigDecimal bookValue, BigDecimal dividendPerShare, BigDecimal dividendYield, BigDecimal eps, BigDecimal revenuePerShareTTM, BigDecimal profitMargin, BigDecimal operatingMarginTTM, BigDecimal returnOnAssetsTTM, BigDecimal returnOnEquityTTM, BigDecimal revenueTTM, BigDecimal grossProfitTTM, BigDecimal dilutedEPSTTM, BigDecimal quarterlyEarningsGrowthYOY, BigDecimal quarterlyRevenueGrowthYOY, BigDecimal analystTargetPrice, Integer analystRatingStrongBuy, Integer analystRatingBuy, Integer analystRatingHold, Integer analystRatingSell, Integer analystRatingStrongSell, BigDecimal trailingPE, BigDecimal forwardPE, BigDecimal priceToSalesRatioTTM, BigDecimal priceToBookRatio, BigDecimal evToRevenue, BigDecimal evToEBITDA, BigDecimal beta, BigDecimal weekHigh52, BigDecimal weekLow52, BigDecimal movingAverage50, BigDecimal movingAverage200, Long sharesOutstanding, LocalDate dividendDate, LocalDate exDividendDate) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.exchange = exchange;
        this.currency = currency;
        this.country = country;
        this.sector = sector;
        this.industry = industry;
        this.fiscalYearEnd = fiscalYearEnd;
        this.latestQuarter = latestQuarter;
        this.marketCapitalization = marketCapitalization;
        this.ebitda = ebitda;
        this.peRatio = peRatio;
        this.pegRatio = pegRatio;
        this.bookValue = bookValue;
        this.dividendPerShare = dividendPerShare;
        this.dividendYield = dividendYield;
        this.eps = eps;
        this.revenuePerShareTTM = revenuePerShareTTM;
        this.profitMargin = profitMargin;
        this.operatingMarginTTM = operatingMarginTTM;
        this.returnOnAssetsTTM = returnOnAssetsTTM;
        this.returnOnEquityTTM = returnOnEquityTTM;
        this.revenueTTM = revenueTTM;
        this.grossProfitTTM = grossProfitTTM;
        this.dilutedEPSTTM = dilutedEPSTTM;
        this.quarterlyEarningsGrowthYOY = quarterlyEarningsGrowthYOY;
        this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
        this.analystTargetPrice = analystTargetPrice;
        this.analystRatingStrongBuy = analystRatingStrongBuy;
        this.analystRatingBuy = analystRatingBuy;
        this.analystRatingHold = analystRatingHold;
        this.analystRatingSell = analystRatingSell;
        this.analystRatingStrongSell = analystRatingStrongSell;
        this.trailingPE = trailingPE;
        this.forwardPE = forwardPE;
        this.priceToSalesRatioTTM = priceToSalesRatioTTM;
        this.priceToBookRatio = priceToBookRatio;
        this.evToRevenue = evToRevenue;
        this.evToEBITDA = evToEBITDA;
        this.beta = beta;
        this.weekHigh52 = weekHigh52;
        this.weekLow52 = weekLow52;
        this.movingAverage50 = movingAverage50;
        this.movingAverage200 = movingAverage200;
        this.sharesOutstanding = sharesOutstanding;
        this.dividendDate = dividendDate;
        this.exDividendDate = exDividendDate;
    }

    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getExchange() { return exchange; }
    public String getCurrency() { return currency; }
    public String getCountry() { return country; }
    public String getSector() { return sector; }
    public String getIndustry() { return industry; }
    public String getFiscalYearEnd() { return fiscalYearEnd; }
    public LocalDate getLatestQuarter() { return latestQuarter; }
    public BigDecimal getMarketCapitalization() { return marketCapitalization; }
    public BigDecimal getEbitda() { return ebitda; }
    public BigDecimal getPeRatio() { return peRatio; }
    public BigDecimal getPegRatio() { return pegRatio; }
    public BigDecimal getBookValue() { return bookValue; }
    public BigDecimal getDividendPerShare() { return dividendPerShare; }
    public BigDecimal getDividendYield() { return dividendYield; }
    public BigDecimal getEps() { return eps; }
    public BigDecimal getRevenuePerShareTTM() { return revenuePerShareTTM; }
    public BigDecimal getProfitMargin() { return profitMargin; }
    public BigDecimal getOperatingMarginTTM() { return operatingMarginTTM; }
    public BigDecimal getReturnOnAssetsTTM() { return returnOnAssetsTTM; }
    public BigDecimal getReturnOnEquityTTM() { return returnOnEquityTTM; }
    public BigDecimal getRevenueTTM() { return revenueTTM; }
    public BigDecimal getGrossProfitTTM() { return grossProfitTTM; }
    public BigDecimal getDilutedEPSTTM() { return dilutedEPSTTM; }
    public BigDecimal getQuarterlyEarningsGrowthYOY() { return quarterlyEarningsGrowthYOY; }
    public BigDecimal getQuarterlyRevenueGrowthYOY() { return quarterlyRevenueGrowthYOY; }
    public BigDecimal getAnalystTargetPrice() { return analystTargetPrice; }
    public Integer getAnalystRatingStrongBuy() { return analystRatingStrongBuy; }
    public Integer getAnalystRatingBuy() { return analystRatingBuy; }
    public Integer getAnalystRatingHold() { return analystRatingHold; }
    public Integer getAnalystRatingSell() { return analystRatingSell; }
    public Integer getAnalystRatingStrongSell() { return analystRatingStrongSell; }
    public BigDecimal getTrailingPE() { return trailingPE; }
    public BigDecimal getForwardPE() { return forwardPE; }
    public BigDecimal getPriceToSalesRatioTTM() { return priceToSalesRatioTTM; }
    public BigDecimal getPriceToBookRatio() { return priceToBookRatio; }
    public BigDecimal getEvToRevenue() { return evToRevenue; }
    public BigDecimal getEvToEBITDA() { return evToEBITDA; }
    public BigDecimal getBeta() { return beta; }
    public BigDecimal getWeekHigh52() { return weekHigh52; }
    public BigDecimal getWeekLow52() { return weekLow52; }
    public BigDecimal getMovingAverage50() { return movingAverage50; }
    public BigDecimal getMovingAverage200() { return movingAverage200; }
    public Long getSharesOutstanding() { return sharesOutstanding; }
    public LocalDate getDividendDate() { return dividendDate; }
    public LocalDate getExDividendDate() { return exDividendDate; }
}