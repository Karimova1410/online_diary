package com.example.demo.controller;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.dto.student.StudentResponse;
import com.example.demo.dto.teacher.TeacherResponse;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping("/getTeacherById/{id}")
    public TeacherResponse getById(@PathVariable Long id, @RequestHeader("Authorization") String token){
        return teacherService.getById(id, token);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacher(@PathVariable Long id,  @RequestHeader("Authorization") String token){
        teacherService.deleteById(id, token);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherResponse> getAllTeachers(@RequestHeader("Authorization") String token){
        return teacherService.getAllTeachers(token);
    }

    @PutMapping("/update/{id}")
    public void updateById(@PathVariable Long id, @RequestBody UserRegisterRequest userRegisterRequest, @RequestHeader("Authorization") String token){
        teacherService.updateById(id, userRegisterRequest, token);
    }




}
