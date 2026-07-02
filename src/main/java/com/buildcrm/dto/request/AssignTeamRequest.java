package com.buildcrm.dto.request;

import java.util.Set;
import java.util.UUID;

public record AssignTeamRequest(
    Set<UUID> employeeIds
) {}
