package com.example.tdd_security_second_demo.authentication.store;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FakeUserJpaRepository implements UserJpaRepository {

    private final HashMap<String, UserEntity> userStore = new HashMap<>();

    @Override
    public void deleteInBatch(Iterable<UserEntity> entities) {
        UserJpaRepository.super.deleteInBatch(entities);
    }

    @Override
    public Optional<UserEntity> findUserEntityByEmail(String email) {
        return userStore.values().stream().filter(
                userEntity -> userEntity.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public List<UserEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<UserEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserEntity getOne(String s) {
        return null;
    }

    @Override
    public UserEntity getById(String s) {
        return null;
    }

    @Override
    public UserEntity getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        userStore.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return userStore.values().stream().filter(
                userEntity -> userEntity.getId().equals(id)
        ).findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String id) {
        userStore.remove(id);
    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
