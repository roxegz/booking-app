package com.spacebooking.userservice.infrastructure.adapters.in.web;

import com.spacebooking.userservice.application.port.in.CreateUserUseCase;
import com.spacebooking.userservice.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return createUserUseCase.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return createUserUseCase.createUser(user);
    }
}