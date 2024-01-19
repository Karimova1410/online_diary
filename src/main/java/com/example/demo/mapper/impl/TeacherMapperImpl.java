package com.example.demo.mapper.impl;

import com.example.demo.dto.student.StudentResponse;
import com.example.demo.dto.teacher.TeacherResponse;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TeacherMapperImpl implements TeacherMapper {
    @Override
    public TeacherResponse toDto(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setId(teacher.getId());
        teacherResponse.setAge(teacher.getAge());
        teacherResponse.setPassword(teacher.getPassword());
        return teacherResponse;

    }

    @Override
    public List<TeacherResponse> toDtoS(List<Teacher> all) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for(Teacher teacher : all){
            teacherResponses.add(toDto(teacher));
        }
        return teacherResponses;
    }
}
