package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.PipelineProject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PipelineUseCase {
    PipelineProject createPipelineProject(PipelineProject pipelineProject);
    PipelineProject updatePipelineProject(UUID id, PipelineProject pipelineProject);
    Optional<PipelineProject> getPipelineProjectById(UUID id);
    List<PipelineProject> getAllPipelineProjects();
    void deletePipelineProject(UUID id);
    List<PipelineProject> getPipelineProjectsByStage(String stage);
}
