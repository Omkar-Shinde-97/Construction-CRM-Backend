package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {
    Project save(Project project);
    Optional<Project> findById(UUID id);
    List<Project> findAll();
    void deleteById(UUID id);
    List<Project> findByStatus(String status);
}
