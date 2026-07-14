package com.buildcrm.dto.request;

import com.buildcrm.enums.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDocumentRequest {

    @NotBlank
    private String title;

    private String description;
}
