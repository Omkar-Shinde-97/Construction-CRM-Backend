package com.buildcrm.mapper;

import com.buildcrm.dto.response.UserResponse;
import com.buildcrm.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-26T20:49:04+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Homebrew)"
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

        userEntity.id( response.getId() );
        userEntity.username( response.getUsername() );
        userEntity.email( response.getEmail() );
        userEntity.fullName( response.getFullName() );
        userEntity.role( response.getRole() );
        userEntity.avatarUrl( response.getAvatarUrl() );
        userEntity.phone( response.getPhone() );
        userEntity.active( response.isActive() );
        userEntity.lastLoginAt( response.getLastLoginAt() );

        return userEntity.build();
    }
}
