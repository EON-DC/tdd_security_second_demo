package com.example.tdd_security_second_demo.authentication.filter;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.domain.UserRole;
import com.example.tdd_security_second_demo.authentication.service.UserService;
import com.example.tdd_security_second_demo.authentication.store.UserJpaStore;
import com.example.tdd_security_second_demo.authentication.store.UserStore;
import com.example.tdd_security_second_demo.share.token.JwtSecretKey;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

public class JwtTokenValidatorTests {

    private MockMvc mockMvc;

    private MockFilterChain mockFilterChain;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;


    private JwtTokenFilter jwtTokenFilter;
    private JwtSecretKey jwtSecretKey;
    private UserStore userStore;
    private User user;


    @BeforeEach
    void setUp() {
        user = User.builder().name("john").password("1234").roles((Arrays.asList(UserRole.ROLE_USER, UserRole.ROLE_ADMIN))).email("john@email.com").build();
        mockFilterChain = new MockFilterChain();
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        userStore= new UserJpaStore()
    }
}
