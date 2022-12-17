package com.example.tdd_security_second_demo.authentication.store;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.exception.NoSuchUserException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserJpaStore implements UserStore{

    private final UserJpaRepository userJpaRepository;

    public UserJpaStore(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> getAll() {
        return userJpaRepository.findAll().stream().map(UserEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public User get(String id) {
        return userJpaRepository.findById(id).orElseThrow(
                () -> new NoSuchUserException("No such user has id :" + id)
        ).toDomain();
    }

    @Override
    public User findUserByEmail(String email) {
        return userJpaRepository.findUserEntityByEmail(email).orElseThrow(
                () -> new RuntimeException("Invalid email or password")
        ).toDomain();
    }

    @Override
    public User add(User user) {
        return userJpaRepository.save(new UserEntity(user)).toDomain();
    }

    @Override
    public User update(User user) {
        return userJpaRepository.save(new UserEntity(user)).toDomain();
    }

    @Override
    public void delete(String id) {
        userJpaRepository.deleteById(id);
    }
}
