package com.buildcrm.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDocumentResponse {
    private UUID id;
    private String name;
    private String type;
    private String size;
    private String fileUrl;
    private OffsetDateTime uploadedAt;
}
