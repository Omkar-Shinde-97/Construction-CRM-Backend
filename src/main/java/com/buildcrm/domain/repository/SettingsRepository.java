package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.CompanySettings;

import java.util.Optional;
import java.util.UUID;

public interface SettingsRepository {
    CompanySettings save(CompanySettings settings);
    Optional<CompanySettings> findById(UUID id);
    Optional<CompanySettings> findCurrent();
}
