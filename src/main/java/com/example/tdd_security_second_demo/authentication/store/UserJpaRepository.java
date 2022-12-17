package com.example.tdd_security_second_demo.authentication.store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findUserEntityByEmail(String email);

}
