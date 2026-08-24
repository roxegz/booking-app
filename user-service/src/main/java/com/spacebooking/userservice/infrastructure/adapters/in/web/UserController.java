package com.spacebooking.userservice.infrastructure.adapters.in.web;

import com.spacebooking.userservice.application.port.in.CreateUserUseCase;
import com.spacebooking.userservice.domain.model.User;
import com.spacebooking.userservice.infrastructure.adapters.in.web.dto.CreateUserRequest;
import com.spacebooking.userservice.infrastructure.adapters.in.web.dto.UserResponse;
import com.spacebooking.userservice.infrastructure.adapters.in.web.mapper.UserWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserWebMapper userWebMapper;

    public UserController(CreateUserUseCase createUserUseCase, UserWebMapper userWebMapper) {
        this.createUserUseCase = createUserUseCase;
        this.userWebMapper = userWebMapper;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return createUserUseCase.getAllUsers().stream()
                .map(userWebMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User domainUser = userWebMapper.toDomain(request);
        User savedUser = createUserUseCase.createUser(domainUser);
        return userWebMapper.toResponse(savedUser);
    }
}