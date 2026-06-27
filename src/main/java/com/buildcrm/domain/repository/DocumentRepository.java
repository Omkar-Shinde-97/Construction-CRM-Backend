package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.DocumentMetadata;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository {
    DocumentMetadata save(DocumentMetadata document);
    Optional<DocumentMetadata> findById(UUID id);
    List<DocumentMetadata> findAll();
    void deleteById(UUID id);
    List<DocumentMetadata> findByProjectId(String projectId);
}
