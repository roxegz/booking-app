package com.spacebooking.userservice.domain.port.out;

import com.spacebooking.userservice.domain.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
}