package com.buildcrm.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class DocumentMetadata {
    private UUID id;
    private String projectId;
    private String name;
    private String type;
    private Long sizeInBytes;
    private LocalDate uploadedAt;
    private String uploadedBy;

    public DocumentMetadata() {
    }

    public DocumentMetadata(UUID id, String projectId, String name, String type,
                            Long sizeInBytes, LocalDate uploadedAt, String uploadedBy) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.type = type;
        this.sizeInBytes = sizeInBytes;
        this.uploadedAt = uploadedAt;
        this.uploadedBy = uploadedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public LocalDate getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDate uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}