package com.buildcrm.entity;

import com.buildcrm.enums.PipelineStage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "pipeline_projects")
@Where(clause = "is_deleted = false")
public class PipelineProjectEntity extends BaseEntity {

    @Id
    private UUID id;

    private String name;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "estimated_value")
    private BigDecimal estimatedValue;

    @Enumerated(EnumType.STRING)
    private PipelineStage stage;

    @Column(name = "probability_percentage")
    private Integer probabilityPercentage;

    @Column(name = "expected_start_date")
    private LocalDate expectedStartDate;

    @Column(name = "next_action_date")
    private LocalDate nextActionDate;

    @Column(name = "next_action_description")
    private String nextActionDescription;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_id")
    private EmployeeEntity assignedTo;

    @Column(name = "kanban_order")
    private Integer kanbanOrder;
}
