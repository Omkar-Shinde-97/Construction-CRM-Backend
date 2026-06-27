package com.buildcrm.repository;

import com.buildcrm.entity.SaleTransactionEntity;
import com.buildcrm.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleTransactionRepository extends JpaRepository<SaleTransactionEntity, UUID> {
    Optional<SaleTransactionEntity> findByIdAndDeletedFalse(UUID id);
    List<SaleTransactionEntity> findByStatusAndDeletedFalse(SaleStatus status);
    List<SaleTransactionEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
