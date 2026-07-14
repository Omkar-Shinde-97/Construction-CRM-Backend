package com.buildcrm.entity;

import com.buildcrm.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
@Where(clause = "is_deleted = false")
public class ExpenseEntity extends BaseEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal amount;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "receipt_image", columnDefinition = "VARBINARY(10485760)")
    private byte[] receiptImage;

    @Column(name = "receipt_file_name")
    private String receiptFileName;

    @Column(name = "receipt_content_type")
    private String receiptContentType;

    @Column(name = "added_by")
    private String addedBy;

    private boolean approved;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
