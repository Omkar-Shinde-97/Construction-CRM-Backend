package com.buildcrm.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectActivityResponse {

    private UUID id;
    private String message;
    private OffsetDateTime activityDate;
    private String performedBy;
}
