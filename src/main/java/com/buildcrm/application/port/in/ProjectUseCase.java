package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectUseCase {
    Project createProject(Project project);
    Project updateProject(UUID id, Project project);
    Optional<Project> getProjectById(UUID id);
    List<Project> getAllProjects();
    void deleteProject(UUID id);
    List<Project> getProjectsByStatus(String status);
}
