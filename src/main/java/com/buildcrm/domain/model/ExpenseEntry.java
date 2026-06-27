package com.buildcrm.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ExpenseEntry {
    private UUID id;
    private String projectId;
    private LocalDate date;
    private ExpenseCategory category;
    private String description;
    private BigDecimal amount;
    private String addedBy;

    public ExpenseEntry() {
    }

    public ExpenseEntry(UUID id, String projectId, LocalDate date, ExpenseCategory category,
                        String description, BigDecimal amount, String addedBy) {
        this.id = id;
        this.projectId = projectId;
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.addedBy = addedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}