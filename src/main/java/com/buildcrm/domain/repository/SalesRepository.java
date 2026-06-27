package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.SalesTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SalesRepository {
    SalesTransaction save(SalesTransaction transaction);
    Optional<SalesTransaction> findById(UUID id);
    List<SalesTransaction> findAll();
    void deleteById(UUID id);
    List<SalesTransaction> findByProjectId(String projectId);
}
