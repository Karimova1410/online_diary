package com.example.demo.controller;

import com.example.demo.dto.grade.GradeRequest;
import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {
    private final GradeService gradeService;
//    public final GradeResponse gradeResponse;

    @PostMapping("/addToStudent/{studentId}")
    public void addGrade(@PathVariable Long studentId, @RequestBody GradeRequest request, @RequestHeader("Authorization") String token){
        gradeService.addGrade(studentId, request, token);
    }
    @GetMapping("/getStudentGrades/{studentId}")
    public List<GradeResponse> studentGrades(@PathVariable Long studentId,  @RequestHeader("Authorization") String token){
        return gradeService.getStudentGrades(studentId, token);
    }

    @GetMapping("/getMyGrades")
    public List<GradeResponse> getMyGrades(@RequestHeader("Authorization") String token){
        return gradeService.getMyGrades(token);
    }


    @GetMapping("/getGrade/{gradeId}")
    public GradeResponse getGradeById(@PathVariable Long gradeId,  @RequestHeader("Authorization") String token){
        return gradeService.getGradeById(gradeId, token);
    }

    @DeleteMapping("/deleteGrade/{gradeId}")
    public void deleteById(@PathVariable Long gradeId, @RequestHeader("Authorization") String token){
        gradeService.deleteById(gradeId, token);
    }

    @PutMapping("/updateGrade/{gradeId}")
    public void updateById(@PathVariable Long gradeId, @RequestBody GradeRequest gradeRequest, @RequestHeader("Authorization") String token){
        gradeService.updateById(gradeId, gradeRequest, token);
    }









}


