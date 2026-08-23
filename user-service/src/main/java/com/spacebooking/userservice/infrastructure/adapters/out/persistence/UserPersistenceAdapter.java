package com.spacebooking.userservice.infrastructure.adapters.out.persistence;

import com.spacebooking.userservice.domain.model.User;
import com.spacebooking.userservice.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserEntity saved = repository.save(entity);
        return new User(saved.getId(), saved.getName(), saved.getEmail(), saved.getCreatedAt());
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(e -> new User(e.getId(), e.getName(), e.getEmail(), e.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id)
                .map(e -> new User(e.getId(), e.getName(), e.getEmail(), e.getCreatedAt()));
    }
}