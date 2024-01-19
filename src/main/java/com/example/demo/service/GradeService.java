package com.example.demo.service;

import com.example.demo.dto.grade.GradeRequest;
import com.example.demo.dto.grade.GradeResponse;

import java.util.List;

public interface GradeService {
    void addGrade(Long studentId, GradeRequest request, String token);

    List<GradeResponse> getStudentGrades(Long studentId, String token);

    GradeResponse getGradeById(Long gradeId, String token);

    void deleteById(Long gradeId, String token);

    void updateById(Long gradeId, GradeRequest gradeRequest, String token);

    List<GradeResponse> getMyGrades(String token);
}
