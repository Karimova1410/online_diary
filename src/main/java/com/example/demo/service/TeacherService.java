package com.example.demo.service;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.teacher.TeacherResponse;

import java.util.List;

public interface TeacherService {
    TeacherResponse getById(Long id, String token);

    void deleteById(Long id, String token);

    List<TeacherResponse> getAllTeachers(String token);

    void updateById(Long id, UserRegisterRequest userRegisterRequest, String token);
}
