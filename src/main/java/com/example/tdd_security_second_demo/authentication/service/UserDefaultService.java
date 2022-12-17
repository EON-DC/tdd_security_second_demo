package com.example.tdd_security_second_demo.authentication.service;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.store.UserJpaStore;
import com.example.tdd_security_second_demo.authentication.store.UserStore;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDefaultService implements UserService, UserDetailsService {

    private final UserJpaStore userJpaStore;

    public UserDefaultService(UserJpaStore userJpaStore) {
        this.userJpaStore = userJpaStore;
    }

    @Override
    public List<User> getAll() {
        return userJpaStore.getAll();
    }

    @Override
    public User get(String id) {
        return userJpaStore.get(id);
    }

    @Override
    public User add(User user) {
        return userJpaStore.add(user);
    }

    @Override
    public User update(String id, NameValueList nameValueList) {
        User findUser = userJpaStore.get(id);
        findUser.setValues(nameValueList);
        userJpaStore.update(findUser);
        return findUser;
    }

    @Override
    public void delete(String id) {
        userJpaStore.delete(id);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userJpaStore.findUserByEmail(email);
        if (findUser != null) {
            return findUser;
        }
        throw new UsernameNotFoundException("Invalid email or password");
    }
}
