package com.example.demo.service.impl;

import com.example.demo.dto.grade.GradeRequest;
import com.example.demo.dto.grade.GradeResponse;
import com.example.demo.entities.Grade;
import com.example.demo.entities.Student;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.BadCredentialsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.repositories.GradeRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final AuthService authService;
    private final GradeMapper gradeMapper;

    @Override
    public void addGrade(Long studentId, GradeRequest request, String token) {
        if (authService.getUsernameFromToken(token).getRole().equals(Role.STUDENT))
            throw new BadCredentialsException("this function is not for students!");

        Optional<Student> studentOp = studentRepository.findById(studentId);
        if(studentOp.isEmpty())
            throw new NotFoundException("This student doesn't exist!", HttpStatus.NOT_FOUND);
        Student student = studentOp.get();
        Grade grade = new Grade();
        grade.setScore(request.getScore());
        grade.setSubject(request.getSubject());
        grade.setDate(request.getDate());
        grade.setTheme(request.getTheme());
        grade.setOwner(student);
        gradeRepository.save(grade);

    }

    @Override
    public List<GradeResponse> getStudentGrades(Long studentId, String token) {
        if (authService.getUsernameFromToken(token).getRole().equals(Role.STUDENT))
            throw new BadCredentialsException("this function is not for students!");

        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty())
            throw new NotFoundException("This student doesn't exist!", HttpStatus.NOT_FOUND);
        return gradeMapper.toDtoS(student.get().getStudentGrades());
    }

    @Override
    public GradeResponse getGradeById(Long gradeId, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.TEACHER) && !authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is not for students!");

        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if (grade.isEmpty())
            throw new NotFoundException("This grade doesn't exist!", HttpStatus.NOT_FOUND);

        return gradeMapper.toDto(grade.get());
    }

    @Override
    public void deleteById(Long gradeId, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.TEACHER) && !authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is not for students!");

        if(gradeRepository.findById(gradeId).isEmpty())
            throw new NotFoundException("grade not found with id:"+gradeId+"!", HttpStatus.BAD_REQUEST);
        gradeRepository.deleteById(gradeId);

    }

    @Override
    public void updateById(Long gradeId, GradeRequest gradeRequest, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.TEACHER) && !authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is not for students!");

        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if(grade.isEmpty())
            throw new NotFoundException("user not found with id:"+gradeId+"!", HttpStatus.BAD_REQUEST);
        grade.get().setDate(gradeRequest.getDate());
        grade.get().setSubject(gradeRequest.getSubject());
        grade.get().setTheme(gradeRequest.getTheme());
        grade.get().setScore(gradeRequest.getScore());
        gradeRepository.save(grade.get());
    }

    @Override
    public List<GradeResponse> getMyGrades(String token) {
        User user = authService.getUsernameFromToken(token);

        if (!user.getRole().equals(Role.STUDENT)) {
            throw new BadCredentialsException("This function is only for students!");
        }

        Optional<Student> student = studentRepository.findByUser(user);
        if (student.isEmpty()) {
            throw new NotFoundException("Student not found!", HttpStatus.NOT_FOUND);
        }

        return gradeMapper.toDtoS(student.get().getStudentGrades());
    }


}
