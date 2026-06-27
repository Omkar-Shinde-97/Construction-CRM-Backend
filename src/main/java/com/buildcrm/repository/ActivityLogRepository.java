package com.buildcrm.repository;

import com.buildcrm.entity.ActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLogEntity, UUID> {
    List<ActivityLogEntity> findByProjectId(UUID projectId);
}
