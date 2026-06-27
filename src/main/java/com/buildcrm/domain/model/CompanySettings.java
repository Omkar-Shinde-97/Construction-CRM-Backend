package com.buildcrm.domain.model;

import java.util.UUID;

public class CompanySettings {
    private UUID id;
    private String companyName;
    private String companyAddress;
    private String gstNumber;
    private String currency;
    private boolean darkModeEnabled;

    public CompanySettings() {
    }

    public CompanySettings(UUID id, String companyName, String companyAddress,
                           String gstNumber, String currency, boolean darkModeEnabled) {
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.gstNumber = gstNumber;
        this.currency = currency;
        this.darkModeEnabled = darkModeEnabled;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }
}