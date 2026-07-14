package com.buildcrm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProjectFileResponse {

    private UUID id;
    private String fileName;
    private String mimeType;
    private byte[] fileData;
}
