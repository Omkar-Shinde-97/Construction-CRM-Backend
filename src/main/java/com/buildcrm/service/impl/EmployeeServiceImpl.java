package com.buildcrm.service.impl;

import com.buildcrm.dto.request.CreateEmployeeRequest;
import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.entity.EmployeeEntity;
import com.buildcrm.enums.Department;
import com.buildcrm.enums.EmployeeRole;
import com.buildcrm.enums.EmployeeStatus;
import com.buildcrm.exception.BadRequestException;
import com.buildcrm.exception.ResourceNotFoundException;
import com.buildcrm.mapper.EmployeeMapper;
import com.buildcrm.repository.EmployeeRepository;
import com.buildcrm.service.interfaces.EmployeeService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    @SuppressWarnings("null")
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        EmployeeEntity employee = EmployeeEntity.builder()
            .id(UUID.randomUUID())
            .employeeCode(request.getEmployeeCode())
            .fullName(request.getFullName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .alternatePhone(request.getAlternatePhone())
            .role(request.getRole())
            .department(request.getDepartment())
            .status(request.getStatus())
            .dateOfBirth(request.getDateOfBirth())
            .joinDate(request.getJoinDate())
            .address(request.getAddress())
            .salary(request.getSalary())
            .build();
        EmployeeEntity savedEmployee = Objects.requireNonNull(employeeRepository.save(employee));
        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeResponse getEmployee(UUID id) {
        return employeeRepository.findByIdAndDeletedFalse(id)
            .map(employeeMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    @Transactional
    public PagedResponse<EmployeeResponse> listEmployees(int page, int size, String sort, String status, String role, String department) {
        Pageable pageable = PageRequest.of(page, size, parseSort(sort));
        var result = employeeRepository.findAll(employeeSpec(status, role, department), pageable);
        return new PagedResponse<>(
            result.getContent().stream().map(employeeMapper::toResponse).collect(Collectors.toList()),
            result.getNumber(),
            result.getSize(),
            result.getTotalElements(),
            result.getTotalPages(),
            result.isFirst(),
            result.isLast()
        );
    }

    private Specification<EmployeeEntity> employeeSpec(String status, String role, String department) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            if (status != null && !status.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), parseEnum(EmployeeStatus.class, status, "status")));
            }
            if (role != null && !role.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("role"), parseEnum(EmployeeRole.class, role, "role")));
            }
            if (department != null && !department.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("department"), parseEnum(Department.class, department, "department")));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    private Sort parseSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "createdAt");
        }
        String[] parts = sort.split(",", 2);
        String property = parts[0].isBlank() ? "createdAt" : parts[0].trim();
        Sort.Direction direction = parts.length > 1 && "asc".equalsIgnoreCase(parts[1].trim())
            ? Sort.Direction.ASC
            : Sort.Direction.DESC;
        return Sort.by(direction, property);
    }

    private <T extends Enum<T>> T parseEnum(Class<T> enumType, String value, String fieldName) {
        try {
            return Enum.valueOf(enumType, value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid " + fieldName + ": " + value);
        }
    }
}
