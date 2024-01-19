package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.student.StudentResponse;
import com.example.demo.entities.Student;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.BadCredentialsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AuthService authService;
    @Override
    public StudentResponse getById(Long id, String token) {
        if (authService.getUsernameFromToken(token).getRole().equals(Role.STUDENT))
            throw new BadCredentialsException("this function is not for students!");
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        return studentMapper.toDto(student.get());
    }

    @Override
    public void deleteById(Long id, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is for admins!");
        if (studentRepository.findById(id).isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> getAllStudents(String token) {
        if (authService.getUsernameFromToken(token).getRole().equals(Role.STUDENT))
            throw new BadCredentialsException("this function is not for students!");
        return studentMapper.toDtoS(studentRepository.findAll());

    }

    @Override
    public void updateById(Long id, UserRegisterRequest userRegisterRequest, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is for admins!");
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        student.get().setPassword(userRegisterRequest.getPassword());
        student.get().setAge(userRegisterRequest.getAge());
        student.get().setFirstName(userRegisterRequest.getFirstName());
        student.get().setLastName(userRegisterRequest.getLastName());
        studentRepository.save(student.get());
    }


}
