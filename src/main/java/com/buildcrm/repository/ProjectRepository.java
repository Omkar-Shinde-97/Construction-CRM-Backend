package com.buildcrm.repository;

import com.buildcrm.entity.ProjectEntity;
import com.buildcrm.enums.ProjectCategory;
import com.buildcrm.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID>, JpaSpecificationExecutor<ProjectEntity> {
    Optional<ProjectEntity> findByIdAndDeletedFalse(UUID id);
    Optional<ProjectEntity> findByProjectCodeAndDeletedFalse(String projectCode);
    List<ProjectEntity> findByStatusAndDeletedFalse(ProjectStatus status);
    List<ProjectEntity> findByCategoryAndDeletedFalse(ProjectCategory category);
}
