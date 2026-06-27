package com.buildcrm.domain.repository;

import com.buildcrm.domain.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(UUID id);
    List<Employee> findAll();
    void deleteById(UUID id);
    List<Employee> findByDepartment(String department);
}
