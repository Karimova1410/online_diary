package com.example.demo.mapper.impl;

import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.entities.Grade;
import com.example.demo.mapper.GradeMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GradeMapperImpl implements GradeMapper {
    @Override
    public GradeResponse toDto(Grade grade) {
        GradeResponse gradeResponse = new GradeResponse();
        gradeResponse.setId(grade.getId());
        gradeResponse.setDate(grade.getDate());
        gradeResponse.setScore(grade.getScore());
        gradeResponse.setTheme(grade.getTheme());
        gradeResponse.setSubject(grade.getSubject());
        return gradeResponse;
    }

    @Override
    public List<GradeResponse> toDtoS(List<Grade> all) {
        List<GradeResponse> gradeResponses = new ArrayList<>();
        for(Grade grade: all){
            gradeResponses.add(toDto(grade));
        }
        return gradeResponses;
    }
}



