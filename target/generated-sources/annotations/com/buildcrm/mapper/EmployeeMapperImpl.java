package com.buildcrm.mapper;

import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.entity.EmployeeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-27T16:25:37+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponse toResponse(EmployeeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setId( entity.getId() );
        employeeResponse.setEmployeeCode( entity.getEmployeeCode() );
        employeeResponse.setFullName( entity.getFullName() );
        employeeResponse.setEmail( entity.getEmail() );
        employeeResponse.setPhone( entity.getPhone() );
        employeeResponse.setAlternatePhone( entity.getAlternatePhone() );
        employeeResponse.setRole( entity.getRole() );
        employeeResponse.setDepartment( entity.getDepartment() );
        employeeResponse.setStatus( entity.getStatus() );
        employeeResponse.setDateOfBirth( entity.getDateOfBirth() );
        employeeResponse.setJoinDate( entity.getJoinDate() );
        employeeResponse.setAddress( entity.getAddress() );
        employeeResponse.setSalary( entity.getSalary() );
        employeeResponse.setAvatarUrl( entity.getAvatarUrl() );
        employeeResponse.setPerformanceRating( entity.getPerformanceRating() );
        employeeResponse.setPerformanceComment( entity.getPerformanceComment() );
        employeeResponse.setCreatedAt( entity.getCreatedAt() );
        employeeResponse.setUpdatedAt( entity.getUpdatedAt() );

        return employeeResponse;
    }
}
