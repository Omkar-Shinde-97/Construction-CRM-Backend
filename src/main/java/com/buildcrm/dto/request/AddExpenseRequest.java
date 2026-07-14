package com.buildcrm.dto.request;

import com.buildcrm.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class AddExpenseRequest {

    @NotNull
    private ExpenseCategory category;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    private LocalDate expenseDate;

    private String vendorName;
    private String addedBy;

}
