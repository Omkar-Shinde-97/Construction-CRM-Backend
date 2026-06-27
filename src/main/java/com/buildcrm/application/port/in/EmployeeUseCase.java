package com.buildcrm.application.port.in;

import com.buildcrm.domain.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeUseCase {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(UUID id, Employee employee);
    Optional<Employee> getEmployeeById(UUID id);
    List<Employee> getAllEmployees();
    void deleteEmployee(UUID id);
    List<Employee> getEmployeesByDepartment(String department);
}
