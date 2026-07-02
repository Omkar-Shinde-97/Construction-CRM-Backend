package com.buildcrm.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectNoteResponse {
    private UUID id;
    private String note;
    private String createdBy;
    private OffsetDateTime createdAt;
}
