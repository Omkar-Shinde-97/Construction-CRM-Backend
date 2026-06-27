package com.buildcrm.repository;

import com.buildcrm.entity.MilestoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MilestoneRepository extends JpaRepository<MilestoneEntity, UUID> {
    Optional<MilestoneEntity> findByIdAndDeletedFalse(UUID id);
    List<MilestoneEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
