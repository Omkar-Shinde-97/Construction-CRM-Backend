package com.buildcrm.mapper;

import com.buildcrm.dto.response.EmployeeResponse;
import com.buildcrm.entity.EmployeeEntity;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponse toResponse(EmployeeEntity entity);
    List<EmployeeResponse> toResponseList(List<EmployeeEntity> entities);
}
