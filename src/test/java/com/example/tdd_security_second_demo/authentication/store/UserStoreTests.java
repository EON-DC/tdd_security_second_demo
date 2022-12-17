package com.example.tdd_security_second_demo.authentication.store;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.domain.UserRole;
import com.example.tdd_security_second_demo.authentication.exception.NoSuchUserException;
import com.example.tdd_security_second_demo.share.domain.NameValue;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserStoreTests {

    private User firstUser;
    private User secondUser;

    private UserStore userStore;
    private UserJpaRepository fakeUserJpaRepository;

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

        fakeUserJpaRepository = new FakeUserJpaRepository();
        userStore = new UserJpaStore(fakeUserJpaRepository);

    }


    @Test
    void tdd_for_getAllUsers_returnsListUser() throws Exception {
        userStore.add(firstUser);
        userStore.add(secondUser);

        List<User> users = userStore.getAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    void tdd_for_getUserWithCorrectId_returnsUser() throws Exception {
        userStore.add(firstUser);

        User findUser = userStore.get(firstUser.getId());
        assertThat(findUser.getEmail()).isEqualTo("john@email.com");

    }

    @Test
    void tdd_for_getUserWithWrongId_throwsException() throws Exception {
        assertThatThrownBy(() -> userStore.get("999")).isInstanceOf(NoSuchUserException.class);

    }

    @Test
    void tdd_for_addUser_returnsUser() throws Exception {
        User add = userStore.add(firstUser);
        assertThat(add.getId()).isEqualTo(firstUser.getId());

    }

    @Test
    void tdd_for_updateUser_returnUser() throws Exception {
        userStore.add(firstUser);
        NameValueList nameValueList = new NameValueList();
        nameValueList.add(new NameValue("name", "park"));
        firstUser.setValues(nameValueList);

        User update = userStore.update(firstUser);
        assertThat(update.getName()).isEqualTo("park");
    }

    @Test
    void tdd_for_deleteUser_returnsUser() throws Exception {
        userStore.add(firstUser);
        userStore.delete(firstUser.getId());
        assertThat(userStore.getAll().size()).isEqualTo(0);

    }

    @Test
    void tdd_for_findUserWithEmail_returnsUser() throws Exception {
        userStore.add(firstUser);

        User findUser = userStore.findUserByEmail("john@email.com");

        assertThat(findUser.getId()).isEqualTo(firstUser.getId());

    }

    @Test
    void tdd_for_findUserWithWrongEmail_throwsException() throws Exception{
        assertThatThrownBy(() -> userStore.findUserByEmail("999")).isInstanceOf(RuntimeException.class);

    }




}
