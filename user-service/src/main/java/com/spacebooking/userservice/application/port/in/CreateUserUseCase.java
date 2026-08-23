package com.spacebooking.userservice.application.port.in;

import com.spacebooking.userservice.domain.model.User;
import java.util.List;

public interface CreateUserUseCase {
    User createUser(User user);
    List<User> getAllUsers();
}