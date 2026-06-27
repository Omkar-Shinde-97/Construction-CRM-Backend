package com.buildcrm.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class PipelineProject {
    private UUID id;
    private String name;
    private String client;
    private BigDecimal estimatedValue;
    private LocalDate expectedStartDate;
    private Integer probability;
    private String owner;
    private PipelineStage stage;
    private String notes;

    public PipelineProject() {
    }

    public PipelineProject(UUID id, String name, String client, BigDecimal estimatedValue,
                           LocalDate expectedStartDate, Integer probability, String owner,
                           PipelineStage stage, String notes) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.estimatedValue = estimatedValue;
        this.expectedStartDate = expectedStartDate;
        this.probability = probability;
        this.owner = owner;
        this.stage = stage;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public LocalDate getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(LocalDate expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PipelineStage getStage() {
        return stage;
    }

    public void setStage(PipelineStage stage) {
        this.stage = stage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}