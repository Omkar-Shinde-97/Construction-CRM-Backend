package com.buildcrm.mapper;

import com.buildcrm.dto.response.*;
import com.buildcrm.entity.ProjectEntity;
import com.buildcrm.entity.ProjectInventoryEntity;
import com.buildcrm.entity.ProjectNoteEntity;
import com.buildcrm.entity.DocumentEntity;
import com.buildcrm.entity.ExpenseEntity;
import com.buildcrm.entity.SaleTransactionEntity;
import com.buildcrm.entity.ActivityLogEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;

import static com.buildcrm.enums.EmployeeRole.LABOR;
import static com.buildcrm.enums.ExpenseCategory.*;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    @Mapping(source = "team", target = "teamMembers")
    @Mapping(source = "contractValue", target = "totalContractValue")
    @Mapping(source = "salesTransactions", target = "transactions")
    @Mapping(source = "inventories", target = "inventories")

    ProjectResponse toResponse(ProjectEntity entity);

    ProjectNoteResponse toNoteResponse(ProjectNoteEntity entity);
    

    ProjectInventoryResponse toInventoryResponse(ProjectInventoryEntity entity);

    @Mapping(source = "invoiceNumber", target = "invoiceNo")
    @Mapping(source = "invoiceDate", target = "date")
    ProjectTransactionResponse toTransactionResponse(SaleTransactionEntity entity);

    @Mapping(source = "expenseDate", target = "date")
    ProjectExpenseResponse toExpenseResponse(ExpenseEntity entity);

    @Mapping(source = "createdAt", target = "activityDate")
    @Mapping(source = "description", target = "message")
    ProjectActivityResponse toActivityResponse(ActivityLogEntity entity);

    @Mapping(source = "originalName", target = "name")
    @Mapping(source = "mimeType", target = "type")
    @Mapping(source = "fileSize", target = "size")
    
    ProjectDocumentResponse toDocumentResponse(DocumentEntity entity);
    ProjectFileResponse toFileResponse(DocumentEntity entity);

    default String mapFileSize(Long size) {
        if (size == null) return "0 B";
        if (size < 1024) return size + " B";
        int z = (63 - Long.numberOfLeadingZeros(size)) / 10;
        return String.format("%.1f %sB", (double)size / (1L << (z * 10)), " KMGTPE".charAt(z));
    }

    @AfterMapping
    default void calculateFinancialsAndSummaries(ProjectEntity entity, @MappingTarget ProjectResponse response) {
        // Calculate financial summaries
        BigDecimal totalSpent = BigDecimal.ZERO;
        BigDecimal laborExpense = BigDecimal.ZERO;
        BigDecimal materialExpense = BigDecimal.ZERO;
        BigDecimal equipmentExpense = BigDecimal.ZERO;
        BigDecimal overheadExpense = BigDecimal.ZERO;

        if (entity.getExpenses() != null) {
            for (ExpenseEntity exp : entity.getExpenses()) {
                if (exp.getAmount() != null) {
                    totalSpent = totalSpent.add(exp.getAmount());
                    if (exp.getCategory() != null) {
                        switch (exp.getCategory()) {
                            case LABOR -> laborExpense = laborExpense.add(exp.getAmount());
                            case MATERIAL -> materialExpense = materialExpense.add(exp.getAmount());
                            case EQUIPMENT -> equipmentExpense = equipmentExpense.add(exp.getAmount());
                            case OVERHEAD -> overheadExpense = overheadExpense.add(exp.getAmount());
                        }
                    }
                }
            }
        }

        response.setTotalSpent(totalSpent);
        response.setLaborExpense(laborExpense);
        response.setMaterialExpense(materialExpense);
        response.setEquipmentExpense(equipmentExpense);
        response.setOverheadExpense(overheadExpense);

        BigDecimal budget = entity.getTotalBudget() != null ? entity.getTotalBudget() : BigDecimal.ZERO;
        response.setRemainingBudget(budget.subtract(totalSpent));

        // Calculate sales summaries
        BigDecimal totalReceived = BigDecimal.ZERO;
        BigDecimal totalPending = BigDecimal.ZERO;
        BigDecimal totalOverdue = BigDecimal.ZERO;

        if (entity.getSalesTransactions() != null) {
            for (SaleTransactionEntity tx : entity.getSalesTransactions()) {
                if (tx.getCollectedAmount() != null) {
                    totalReceived = totalReceived.add(tx.getCollectedAmount());
                }
                BigDecimal txAmount = tx.getAmount() != null ? tx.getAmount() : BigDecimal.ZERO;
                BigDecimal txCollected = tx.getCollectedAmount() != null ? tx.getCollectedAmount() : BigDecimal.ZERO;
                BigDecimal pendingForTx = txAmount.subtract(txCollected);
                if (pendingForTx.compareTo(BigDecimal.ZERO) > 0) {
                    totalPending = totalPending.add(pendingForTx);
                    if (tx.getStatus() == com.buildcrm.enums.SaleStatus.OVERDUE) {
                        totalOverdue = totalOverdue.add(pendingForTx);
                    }
                }
            }
        }

        response.setTotalReceived(totalReceived);
        response.setTotalPending(totalPending);
        response.setTotalOverdue(totalOverdue);

        // Map contract value
        response.setTotalContractValue(entity.getContractValue());

        // Calculate inventory summaries
        int totalInv = 0;
        int availableInv = 0;
        int soldInv = 0;
        int blockedInv = 0;

        if (entity.getInventories() != null) {
            totalInv = entity.getInventories().size();
            for (ProjectInventoryEntity inv : entity.getInventories()) {
                if (inv.getStatus() != null) {
                    String status = inv.getStatus().trim().toUpperCase();
                    if ("AVAILABLE".equals(status)) {
                        availableInv++;
                    } else if ("SOLD".equals(status)) {
                        soldInv++;
                    } else if ("BLOCKED".equals(status)) {
                        blockedInv++;
                    }
                }
            }
        }

        response.setTotalInventory(totalInv);
        response.setAvailableInventory(availableInv);
        response.setSoldInventory(soldInv);
        response.setBlockedInventory(blockedInv);
    }
}
