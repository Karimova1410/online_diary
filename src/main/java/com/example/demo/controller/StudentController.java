package com.example.demo.controller;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.dto.student.StudentResponse;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/getStudentById/{id}")
    public StudentResponse getById(@PathVariable Long id, @RequestHeader("Authorization") String token){
        return studentService.getById(id, token);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id,  @RequestHeader("Authorization") String token){
        studentService.deleteById(id, token);
    }

    @GetMapping("/getAllStudents")
    public List<StudentResponse> getAllStudents(@RequestHeader("Authorization") String token){
        return studentService.getAllStudents(token);
    }

    @PutMapping("/update/{id}")
    public void updateById(@PathVariable Long id, @RequestBody UserRegisterRequest userRegisterRequest, @RequestHeader("Authorization") String token){
        studentService.updateById(id,userRegisterRequest, token);
    }




}
