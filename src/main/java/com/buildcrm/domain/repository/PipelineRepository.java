package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.PipelineProject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PipelineRepository {
    PipelineProject save(PipelineProject pipeline);
    Optional<PipelineProject> findById(UUID id);
    List<PipelineProject> findAll();
    void deleteById(UUID id);
    List<PipelineProject> findByStage(String stage);
}
