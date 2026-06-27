package com.buildcrm.mapper;

import com.buildcrm.dto.response.UserResponse;
import com.buildcrm.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(UserEntity entity);
    UserEntity toEntity(UserResponse response);
}
