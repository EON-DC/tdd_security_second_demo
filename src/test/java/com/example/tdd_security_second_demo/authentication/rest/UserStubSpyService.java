package com.example.tdd_security_second_demo.authentication.rest;


import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.service.UserService;
import com.example.tdd_security_second_demo.authentication.store.UserStore;
import com.example.tdd_security_second_demo.share.domain.NameValueList;

import java.util.List;

public class UserStubSpyService implements UserService {

    private String get_argument_id;
    private String update_argument_id;
    private String delete_argument_id;
    private NameValueList update_argument_body;
    private User add_argument_body;

    private List<User> getAll_returnsValue;
    private User get_returnsValue;
    private User add_returnsValue;
    private User update_returnsValue;


    public void setGetAll_returnsValue(List<User> getAll_returnsValue) {
        this.getAll_returnsValue = getAll_returnsValue;
    }

    public void setGet_returnsValue(User get_returnsValue) {
        this.get_returnsValue = get_returnsValue;
    }

    public void setAdd_returnsValue(User add_returnsValue) {
        this.add_returnsValue = add_returnsValue;
    }

    public void setUpdate_returnsValue(User update_returnsValue) {
        this.update_returnsValue = update_returnsValue;
    }

    public String getGet_argument_id() {
        return get_argument_id;
    }

    public String getUpdate_argument_id() {
        return update_argument_id;
    }

    public String getDelete_argument_id() {
        return delete_argument_id;
    }

    public NameValueList getUpdate_argument_body() {
        return update_argument_body;
    }

    public User getAdd_argument_body() {
        return add_argument_body;
    }
    @Override
    public List<User> getAll() {
        return getAll_returnsValue;
    }

    @Override
    public User get(String id) {
        get_argument_id = id;
        return get_returnsValue;
    }

    @Override
    public User add(User user) {
        add_argument_body = user;
        return add_returnsValue;
    }

    @Override
    public User update(String id, NameValueList nameValueList) {
        update_argument_id = id;
        update_argument_body = nameValueList;
        return update_returnsValue;
    }

    @Override
    public void delete(String id) {
        delete_argument_id = id;
    }
}
