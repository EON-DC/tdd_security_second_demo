package com.example.tdd_security_second_demo.authentication.service;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.share.domain.NameValueList;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(String id);

    User add(User user);

    User update(String id, NameValueList nameValueList);

    void delete(String id);
}
