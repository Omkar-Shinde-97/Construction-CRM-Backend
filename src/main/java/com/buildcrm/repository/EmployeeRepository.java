package com.buildcrm.repository;

import com.buildcrm.entity.EmployeeEntity;
import com.buildcrm.enums.Department;
import com.buildcrm.enums.EmployeeRole;
import com.buildcrm.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByIdAndDeletedFalse(UUID id);
    List<EmployeeEntity> findByStatusAndDeletedFalse(EmployeeStatus status);
    List<EmployeeEntity> findByRoleAndDeletedFalse(EmployeeRole role);
    List<EmployeeEntity> findByDepartmentAndDeletedFalse(Department department);
}
