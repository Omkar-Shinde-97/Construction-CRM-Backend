package com.buildcrm.application.service;

import java.math.BigDecimal;

public class FinanceOverview {
    private String projectName;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;
    private BigDecimal grossProfit;
    private BigDecimal netProfit;
    private BigDecimal marginPercent;

    public FinanceOverview() {
    }

    public FinanceOverview(String projectName, BigDecimal totalRevenue, BigDecimal totalExpenses,
                           BigDecimal grossProfit, BigDecimal netProfit, BigDecimal marginPercent) {
        this.projectName = projectName;
        this.totalRevenue = totalRevenue;
        this.totalExpenses = totalExpenses;
        this.grossProfit = grossProfit;
        this.netProfit = netProfit;
        this.marginPercent = marginPercent;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getMarginPercent() {
        return marginPercent;
    }

    public void setMarginPercent(BigDecimal marginPercent) {
        this.marginPercent = marginPercent;
    }
}