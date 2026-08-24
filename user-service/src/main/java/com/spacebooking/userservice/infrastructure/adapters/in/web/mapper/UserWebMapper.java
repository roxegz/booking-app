package com.spacebooking.userservice.infrastructure.adapters.in.web.mapper;

import com.spacebooking.userservice.domain.model.User;
import com.spacebooking.userservice.infrastructure.adapters.in.web.dto.CreateUserRequest;
import com.spacebooking.userservice.infrastructure.adapters.in.web.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

    public User toDomain(CreateUserRequest request) {
        return new User(null, request.getName(), request.getEmail(), null);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }
}