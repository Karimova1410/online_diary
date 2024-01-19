package com.example.demo.mapper.impl;

import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.dto.student.StudentResponse;
import com.example.demo.entities.Grade;
import com.example.demo.entities.Student;
import com.example.demo.mapper.StudentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentResponse toDto(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setAge(student.getAge());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setId(student.getId());
        return studentResponse;
    }

    @Override
    public List<StudentResponse> toDtoS(List<Student> all) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(Student student : all){
            studentResponses.add(toDto(student));
        }
        return studentResponses;
    }


}
