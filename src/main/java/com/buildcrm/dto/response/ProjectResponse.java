package com.buildcrm.dto.response;

import com.buildcrm.enums.ProjectCategory;
import com.buildcrm.enums.ProjectStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProjectResponse {

    private UUID id;
    private String projectCode;
    private String name;
    private String description;
    private String location;

    private String clientName;
    private String clientEmail;
    private String clientPhone;

    private ProjectCategory category;
    private ProjectStatus status;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;

    private BigDecimal totalBudget;
    private BigDecimal totalSpent;
    private BigDecimal remainingBudget;
    private BigDecimal totalContractValue;
    private BigDecimal totalReceived;
    private BigDecimal totalPending;
    private BigDecimal totalOverdue;

    private Integer completionPercentage;

    private String coverImageUrl;

    private EmployeeResponse projectManager;
    private Set<EmployeeResponse> teamMembers;

    private Integer totalInventory;
    private Integer availableInventory;
    private Integer soldInventory;
    private Integer blockedInventory;

    private List<ProjectInventoryResponse> inventories;
    private List<ProjectTransactionResponse> transactions;

    private BigDecimal laborExpense;
    private BigDecimal materialExpense;
    private BigDecimal equipmentExpense;
    private BigDecimal overheadExpense;

    private List<ProjectExpenseResponse> expenses;
    private List<ProjectDocumentResponse> documents;
    private List<ProjectActivityResponse> activities;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    // Getters & Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public BigDecimal getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(BigDecimal remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public BigDecimal getTotalContractValue() {
        return totalContractValue;
    }

    public void setTotalContractValue(BigDecimal totalContractValue) {
        this.totalContractValue = totalContractValue;
    }

    public BigDecimal getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(BigDecimal totalReceived) {
        this.totalReceived = totalReceived;
    }

    public BigDecimal getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(BigDecimal totalPending) {
        this.totalPending = totalPending;
    }

    public BigDecimal getTotalOverdue() {
        return totalOverdue;
    }

    public void setTotalOverdue(BigDecimal totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    public Integer getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(Integer completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public EmployeeResponse getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(EmployeeResponse projectManager) {
        this.projectManager = projectManager;
    }

    public Set<EmployeeResponse> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<EmployeeResponse> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    public Integer getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(Integer availableInventory) {
        this.availableInventory = availableInventory;
    }

    public Integer getSoldInventory() {
        return soldInventory;
    }

    public void setSoldInventory(Integer soldInventory) {
        this.soldInventory = soldInventory;
    }

    public Integer getBlockedInventory() {
        return blockedInventory;
    }

    public void setBlockedInventory(Integer blockedInventory) {
        this.blockedInventory = blockedInventory;
    }

    public List<ProjectInventoryResponse> getInventories() {
        return inventories;
    }

    public void setInventories(List<ProjectInventoryResponse> inventories) {
        this.inventories = inventories;
    }

    public List<ProjectTransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<ProjectTransactionResponse> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getLaborExpense() {
        return laborExpense;
    }

    public void setLaborExpense(BigDecimal laborExpense) {
        this.laborExpense = laborExpense;
    }

    public BigDecimal getMaterialExpense() {
        return materialExpense;
    }

    public void setMaterialExpense(BigDecimal materialExpense) {
        this.materialExpense = materialExpense;
    }

    public BigDecimal getEquipmentExpense() {
        return equipmentExpense;
    }

    public void setEquipmentExpense(BigDecimal equipmentExpense) {
        this.equipmentExpense = equipmentExpense;
    }

    public BigDecimal getOverheadExpense() {
        return overheadExpense;
    }

    public void setOverheadExpense(BigDecimal overheadExpense) {
        this.overheadExpense = overheadExpense;
    }

    public List<ProjectExpenseResponse> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ProjectExpenseResponse> expenses) {
        this.expenses = expenses;
    }

    public List<ProjectDocumentResponse> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ProjectDocumentResponse> documents) {
        this.documents = documents;
    }

    public List<ProjectActivityResponse> getActivities() {
        return activities;
    }

    public void setActivities(List<ProjectActivityResponse> activities) {
        this.activities = activities;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
