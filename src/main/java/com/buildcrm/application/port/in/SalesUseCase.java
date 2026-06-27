package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.SalesTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SalesUseCase {
    SalesTransaction createSalesTransaction(SalesTransaction transaction);
    Optional<SalesTransaction> getSalesTransactionById(UUID id);
    List<SalesTransaction> getAllSalesTransactions();
    void deleteSalesTransaction(UUID id);
    List<SalesTransaction> getSalesTransactionsByProjectId(String projectId);
}
