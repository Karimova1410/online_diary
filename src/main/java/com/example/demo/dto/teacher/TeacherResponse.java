package com.example.demo.dto.teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String password;
}
