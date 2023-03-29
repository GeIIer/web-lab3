package com.ssau.study.authorization.dto;

import com.ssau.study.authorization.entities.Role;
import com.ssau.study.authorization.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    public UserPojo fromEntity(UserEntity entity) {
        return UserPojo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole().name())
                .build();
    }

    public UserEntity toEntity(UserPojo dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(Role.valueOf(dto.getRole()));
        return user;
    }
}
