package com.example.tdd_security_second_demo.authentication.rest.domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDto {

    private String email;
    private String password;

}
