package com.buildcrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_inventories")
@Where(clause = "is_deleted = false")
public class ProjectInventoryEntity extends BaseEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "unit_no", nullable = false)
    private String unitNo;

    @Column(nullable = false)
    private String type; // Flat, Plot, NA Plot

    private BigDecimal area;

    private BigDecimal price;

    @Column(nullable = false)
    private String status; // Available, Sold, Blocked

    private BigDecimal totalCost;
}
