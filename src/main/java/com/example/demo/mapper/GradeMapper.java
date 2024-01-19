package com.example.demo.mapper;

import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.entities.Grade;

import java.util.List;

public interface GradeMapper {
    GradeResponse toDto(Grade grade);
    List<GradeResponse> toDtoS(List<Grade> all);

}
