package com.buildcrm.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class SalesTransaction {
    private UUID id;
    private String invoiceNumber;
    private String projectId;
    private LocalDate date;
    private String description;
    private BigDecimal amount;
    private BigDecimal collected;
    private BigDecimal pending;
    private SalesStatus status;

    public SalesTransaction() {
    }

    public SalesTransaction(UUID id, String invoiceNumber, String projectId, LocalDate date,
                            String description, BigDecimal amount, BigDecimal collected,
                            BigDecimal pending, SalesStatus status) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.projectId = projectId;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.collected = collected;
        this.pending = pending;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public BigDecimal getCollected() {
        return collected;
    }

    public void setCollected(BigDecimal collected) {
        this.collected = collected;
    }

    public BigDecimal getPending() {
        return pending;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }

    public SalesStatus getStatus() {
        return status;
    }

    public void setStatus(SalesStatus status) {
        this.status = status;
    }
}