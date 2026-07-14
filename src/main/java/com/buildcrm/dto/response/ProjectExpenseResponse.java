package com.buildcrm.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectExpenseResponse {
    private UUID id;
    private LocalDate date;
    private String category; // Labor, Material, Equipment, Overhead
    private String description;
    private BigDecimal amount;
    private String addedBy;
    private byte[] receiptImage;
    private String receiptFileName;
    private String receiptContentType;
    private String vendorName;
}
