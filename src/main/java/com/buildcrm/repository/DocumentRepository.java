package com.buildcrm.repository;

import com.buildcrm.entity.DocumentEntity;
import com.buildcrm.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
    Optional<DocumentEntity> findByIdAndDeletedFalse(UUID id);
    List<DocumentEntity> findByProjectIdAndDeletedFalse(UUID projectId);
    List<DocumentEntity> findByEmployeeIdAndDeletedFalse(UUID employeeId);
    List<DocumentEntity> findByDocumentTypeAndDeletedFalse(DocumentType documentType);
}
