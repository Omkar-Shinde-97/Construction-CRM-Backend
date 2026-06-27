package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.DocumentMetadata;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentUseCase {
    DocumentMetadata createDocument(DocumentMetadata document);
    Optional<DocumentMetadata> getDocumentById(UUID id);
    List<DocumentMetadata> getAllDocuments();
    void deleteDocument(UUID id);
    List<DocumentMetadata> getDocumentsByProjectId(String projectId);
}
