package com.example.tdd_security_second_demo.authentication.service;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.domain.UserRole;
import com.example.tdd_security_second_demo.authentication.exception.NoSuchUserException;
import com.example.tdd_security_second_demo.authentication.store.FakeUserJpaRepository;
import com.example.tdd_security_second_demo.authentication.store.UserEntity;
import com.example.tdd_security_second_demo.authentication.store.UserJpaStore;
import com.example.tdd_security_second_demo.share.domain.NameValue;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    private UserJpaStore mockUserJpaStore;

    private UserService userService;

    private User firstUser;
    private User secondUser;

    @BeforeEach
    void setUp() throws Exception {
        firstUser = User.builder()
                .name("john")
                .password("1234")
                .roles((Arrays.asList(UserRole.ROLE_USER, UserRole.ROLE_ADMIN)))
                .email("john@email.com")
                .build();
        secondUser = User.builder()
                .name("ben")
                .password("password")
                .roles((List.of(UserRole.ROLE_USER)))
                .email("john@email.com")
                .build();
        mockUserJpaStore = mock(UserJpaStore.class);
        userService = new UserDefaultService(mockUserJpaStore);
    }

    @Test
    void tdd_for_getAllUsers_returnListUsers() throws Exception {
        when(mockUserJpaStore.getAll()).thenReturn(
                Arrays.asList(firstUser, secondUser)
        );

        List<User> users = userService.getAll();

        assertThat(users.size()).isEqualTo(2);
        List<String> usernames = users.stream().map(User::getName).toList();
        assertThat(usernames.contains("john")).isTrue();
        assertThat(usernames.contains("ben")).isTrue();
    }

    @Test
    void tdd_for_getUser_returnsUser() throws Exception {
        when(mockUserJpaStore.get(anyString())).thenReturn(firstUser);

        User findUser = userService.get("1");

        assertThat(findUser.getName()).isEqualTo("john");
    }


    @Test
    void tdd_for_addUser_returnsUser() throws Exception {
        when(mockUserJpaStore.add(any())).thenReturn(firstUser);

        User addUser = userService.add(firstUser);

        assertThat(addUser.getId()).isEqualTo(firstUser.getId());

    }

    @Test
    void tdd_for_updateUser_returnsUser() throws Exception {
        when(mockUserJpaStore.get(any())).thenReturn(firstUser);
        when(mockUserJpaStore.update(any())).thenReturn(firstUser);
        NameValueList nameValueList = new NameValueList();
        nameValueList.add(new NameValue("name", "jaso"));
        User updateUser = userService.update("0", nameValueList);
        assertThat(updateUser.getId()).isEqualTo(firstUser.getId());
    }

    @Test
    void tdd_for_deleteUser_deletesUser() throws Exception {
        userService.delete("000");
        verify(mockUserJpaStore, times(1)).delete("000");
    }


}
