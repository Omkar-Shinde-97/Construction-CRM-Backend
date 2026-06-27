package com.buildcrm.repository;

import com.buildcrm.entity.ExpenseEntity;
import com.buildcrm.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID> {
    Optional<ExpenseEntity> findByIdAndDeletedFalse(UUID id);
    List<ExpenseEntity> findByProjectIdAndDeletedFalse(UUID projectId);
    List<ExpenseEntity> findByCategoryAndDeletedFalse(ExpenseCategory category);
}
