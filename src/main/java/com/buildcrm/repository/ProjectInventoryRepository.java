package com.buildcrm.repository;

import com.buildcrm.entity.ProjectInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectInventoryRepository extends JpaRepository<ProjectInventoryEntity, UUID> {
    Optional<ProjectInventoryEntity> findByIdAndDeletedFalse(UUID id);
    List<ProjectInventoryEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
