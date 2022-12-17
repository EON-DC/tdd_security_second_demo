package com.example.tdd_security_second_demo.authentication.domain;

import com.example.tdd_security_second_demo.order.domain.Order;
import com.example.tdd_security_second_demo.share.domain.NameValue;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
public class User implements UserDetails {


    private String id;
    private String name;
    private String email;
    private String password;
    private Timestamp creationTime;

    private List<Order> orders;
    private List<UserRole> roles;

    public User() {
        id = UUID.randomUUID().toString();
        creationTime = new Timestamp(new Date().getTime());
        orders = new ArrayList<>();
        roles = new ArrayList<>();
    }

    @Builder
    public User(String name, String email, String password, List<Order> orders, List<UserRole> roles) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
        this.orders = orders;
        this.roles = roles;
    }

    public void setValues(NameValueList nameValueList) {
        for (NameValue nameValue : nameValueList.getNameValues()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "name":
                    this.name = value;
                    break;
                case "email":
                    this.email = value;
                    break;
                case "password":
                    this.password = value;
                    break;
                default:
                    throw new RuntimeException("No such field : " + name);
            }
        }
    }

    public List<String> getUserRoleAsString() {
        return roles.stream().map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
