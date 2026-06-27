package com.buildcrm.controller;

import com.buildcrm.dto.request.CreateEmployeeRequest;
import com.buildcrm.dto.response.ApiResponse;
import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.dto.response.PagedResponse;
import com.buildcrm.service.interfaces.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee created", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(@PathVariable UUID id) {
        EmployeeResponse response = employeeService.getEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee retrieved", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<EmployeeResponse>>> listEmployees(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt,desc") String sort,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String role,
        @RequestParam(required = false) String department
    ) {
        PagedResponse<EmployeeResponse> response = employeeService.listEmployees(page, size, sort, status, role, department);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employees listed", response));
    }
}
