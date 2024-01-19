package com.example.demo.service;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.student.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {


    StudentResponse getById(Long id, String token);

    void deleteById(Long id, String token);


    List<StudentResponse> getAllStudents(String token);

    void updateById(Long id, UserRegisterRequest userRegisterRequest, String token);
}
