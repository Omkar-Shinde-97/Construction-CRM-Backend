package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.CompanySettings;

import java.util.Optional;
import java.util.UUID;

public interface SettingsUseCase {
    CompanySettings updateSettings(CompanySettings settings);
    Optional<CompanySettings> getSettingsById(UUID id);
    Optional<CompanySettings> getCurrentSettings();
}
