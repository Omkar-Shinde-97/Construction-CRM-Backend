package com.buildcrm.repository;

import com.buildcrm.entity.CompanySettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanySettingsRepository extends JpaRepository<CompanySettingsEntity, UUID> {
    Optional<CompanySettingsEntity> findByIdAndDeletedFalse(UUID id);
}
