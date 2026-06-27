package com.buildcrm.mapper;

import com.buildcrm.dto.response.UserResponse;
import com.buildcrm.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-27T16:25:37+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( entity.getId() );
        userResponse.setUsername( entity.getUsername() );
        userResponse.setEmail( entity.getEmail() );
        userResponse.setFullName( entity.getFullName() );
        userResponse.setRole( entity.getRole() );
        userResponse.setAvatarUrl( entity.getAvatarUrl() );
        userResponse.setPhone( entity.getPhone() );
        userResponse.setActive( entity.isActive() );
        userResponse.setLastLoginAt( entity.getLastLoginAt() );

        return userResponse;
    }

    @Override
    public UserEntity toEntity(UserResponse response) {
        if ( response == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.active( response.isActive() );
        userEntity.avatarUrl( response.getAvatarUrl() );
        userEntity.email( response.getEmail() );
        userEntity.fullName( response.getFullName() );
        userEntity.id( response.getId() );
        userEntity.lastLoginAt( response.getLastLoginAt() );
        userEntity.phone( response.getPhone() );
        userEntity.role( response.getRole() );
        userEntity.username( response.getUsername() );

        return userEntity.build();
    }
}
