package com.buildcrm.dto.request;

public record AddNoteRequest(
    String note,
    String createdBy
){}
