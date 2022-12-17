package com.example.tdd_security_second_demo.authentication.rest;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.domain.UserRole;
import com.example.tdd_security_second_demo.share.domain.NameValue;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import com.example.tdd_security_second_demo.share.json.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTests {

    private User user;
    private String userId;
    private UserStubSpyService userStubSpyService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        user = User.builder().name("john").password("1234").roles((Arrays.asList(UserRole.ROLE_USER, UserRole.ROLE_ADMIN))).email("john@email.com").build();
        userId = user.getId();
        userStubSpyService = new UserStubSpyService();
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userStubSpyService)).build();
    }

    @Test
    void tdd_for_getAllUsers_returnsOk() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }


    @Test
    void tdd_for_getAllUsers_returnsListUsers() throws Exception {
        userStubSpyService.setGetAll_returnsValue(Arrays.asList(user, User.builder().name("sam").build()));

        String contentAsString = mockMvc.perform(get("/users")).andReturn()
                .getResponse().getContentAsString();
        List<User> users = JsonUtil.fromJsonList(contentAsString, User.class);
        assertThat(users.size()).isEqualTo(2);
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        assertThat(names.contains("john")).isTrue();
        assertThat(names.contains("sam")).isTrue();
    }

    @Test
    void tdd_for_getUser_returnsOk() throws Exception {
        userStubSpyService.setGet_returnsValue(user);
        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void tdd_for_getUser_returnsUser() throws Exception {
        userStubSpyService.setGet_returnsValue(user);
        String contentAsString = mockMvc.perform(get("/users/" + user.getId()))
                .andReturn().getResponse().getContentAsString();
        User findUser = JsonUtil.fromJson(contentAsString, User.class);
        assertThat(findUser.getId()).isEqualTo(user.getId());
    }


    @Test
    void tdd_for_addUser_returnsOk() throws Exception {
        mockMvc.perform(post("/users")
                .content(JsonUtil.toJson(user))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void tdd_for_addUser_returnsUser() throws Exception {
        userStubSpyService.setAdd_returnsValue(user);
        String contentAsString = mockMvc.perform(post("/users")
                .content(JsonUtil.toJson(user))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse().getContentAsString();

        User findUser = JsonUtil.fromJson(contentAsString, User.class);
        assertThat(findUser.getId()).isEqualTo(user.getId());

    }

    @Test
    void tdd_for_updateUser_returnsUser() throws Exception {
        userStubSpyService.setUpdate_returnsValue(user);
        NameValueList nameValueList = new NameValueList();
        nameValueList.add(new NameValue());
        String contentAsString = mockMvc.perform(patch("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user)))
                .andReturn().getResponse().getContentAsString();
        User updatedUser = JsonUtil.fromJson(contentAsString, User.class);

        assertThat(updatedUser.getId()).isEqualTo(userId);
    }

    @Test
    void tdd_for_updateUser_returnsOk() throws Exception {
        mockMvc.perform(patch("/users/" + userId))
                .andExpect(status().isOk());

    }

    @Test
    void tdd_for_deleteUser_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    void tdd_for_deleteUser_deleteUsers() throws Exception {
        mockMvc.perform(delete("/users/" + userId));

        assertThat(userStubSpyService.getDelete_argument_id()).isEqualTo(userId);

    }


}
