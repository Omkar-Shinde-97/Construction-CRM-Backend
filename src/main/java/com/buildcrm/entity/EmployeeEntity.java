package com.buildcrm.entity;

import com.buildcrm.enums.Department;
import com.buildcrm.enums.EmployeeRole;
import com.buildcrm.enums.EmployeeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@Where(clause = "is_deleted = false")
public class EmployeeEntity extends BaseEntity {

    @Id
    private UUID id;

    @Column(name = "employee_code", nullable = false, unique = true)
    private String employeeCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(name = "alternate_phone")
    private String alternatePhone;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(columnDefinition = "TEXT")
    private String address;

    private BigDecimal salary;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "performance_rating")
    private Double performanceRating;

    @Column(name = "performance_comment")
    private String performanceComment;

    @ManyToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ProjectEntity> projects = new HashSet<>();
}
