package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private String role;
    private Integer age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}

