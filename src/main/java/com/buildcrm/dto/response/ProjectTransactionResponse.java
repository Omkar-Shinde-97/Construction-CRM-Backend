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
public class ProjectTransactionResponse {
    private UUID id;
    private String invoiceNo;
    private LocalDate date;
    private String description;
    private BigDecimal amount;
    private BigDecimal collectedAmount;
    private String status; // Paid, Pending, Overdue
}
