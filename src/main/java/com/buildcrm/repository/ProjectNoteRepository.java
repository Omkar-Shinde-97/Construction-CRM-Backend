package com.buildcrm.repository;

import com.buildcrm.entity.ProjectNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectNoteRepository extends JpaRepository<ProjectNoteEntity, UUID> {
    Optional<ProjectNoteEntity> findByIdAndDeletedFalse(UUID id);
    List<ProjectNoteEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
