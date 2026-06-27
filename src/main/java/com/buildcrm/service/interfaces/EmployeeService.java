package com.buildcrm.service.interfaces;

import com.buildcrm.dto.request.CreateEmployeeRequest;
import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.dto.response.PagedResponse;

import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest request);
    EmployeeResponse getEmployee(UUID id);
    PagedResponse<EmployeeResponse> listEmployees(int page, int size, String sort, String status, String role, String department);
}
