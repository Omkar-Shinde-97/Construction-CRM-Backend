package com.buildcrm.repository;

import com.buildcrm.entity.PipelineProjectEntity;
import com.buildcrm.enums.PipelineStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PipelineProjectRepository extends JpaRepository<PipelineProjectEntity, UUID> {
    Optional<PipelineProjectEntity> findByIdAndDeletedFalse(UUID id);
    List<PipelineProjectEntity> findByStageAndDeletedFalse(PipelineStage stage);
}
