package com.buildcrm.mapper;

import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.entity.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-08T20:51:58+0530",
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

    @Override
    public List<EmployeeResponse> toResponseList(List<EmployeeEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EmployeeResponse> list = new ArrayList<EmployeeResponse>( entities.size() );
        for ( EmployeeEntity employeeEntity : entities ) {
            list.add( toResponse( employeeEntity ) );
        }

        return list;
    }
}
