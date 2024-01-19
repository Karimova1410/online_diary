package com.example.demo.mapper;

import com.example.demo.dto.student.StudentResponse;
import com.example.demo.dto.teacher.TeacherResponse;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;

import java.util.List;

public interface TeacherMapper {
    TeacherResponse toDto(Teacher teacher);
    List<TeacherResponse> toDtoS(List<Teacher> all);
}
