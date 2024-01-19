package com.example.demo.mapper;

import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.dto.student.StudentResponse;
import com.example.demo.entities.Grade;
import com.example.demo.entities.Student;

import java.util.List;

public interface StudentMapper {
    StudentResponse toDto(Student student);
    List<StudentResponse> toDtoS(List<Student> all);
}
