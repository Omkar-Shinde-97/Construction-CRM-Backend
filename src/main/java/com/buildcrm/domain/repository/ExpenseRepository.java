package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.ExpenseEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    ExpenseEntry save(ExpenseEntry expense);
    Optional<ExpenseEntry> findById(UUID id);
    List<ExpenseEntry> findAll();
    void deleteById(UUID id);
    List<ExpenseEntry> findByProjectId(String projectId);
}
