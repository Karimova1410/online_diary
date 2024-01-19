package com.example.demo.entities;

import com.example.demo.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    private User user;

}
