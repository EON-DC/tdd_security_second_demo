package com.example.tdd_security_second_demo.authentication.store;

import com.example.tdd_security_second_demo.authentication.domain.User;

import java.util.List;

public interface UserStore {

    List<User> getAll();

    User get(String id);

    User add(User user);

    User update(User user);

    void delete(String id);

    User findUserByEmail(String email);
}
