package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.teacher.TeacherResponse;
import com.example.demo.entities.Teacher;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.BadCredentialsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final AuthService authService;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    @Override
    public TeacherResponse getById(Long id, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is for admins");
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        return teacherMapper.toDto(teacher.get());
    }

    @Override
    public void deleteById(Long id, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is not for students!");
        if (teacherRepository.findById(id).isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherResponse> getAllTeachers(String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is not for students!");
        return teacherMapper.toDtoS(teacherRepository.findAll());

    }

    @Override
    public void updateById(Long id, UserRegisterRequest userRegisterRequest, String token) {
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function is for admins!");
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty())
            throw new NotFoundException("student not found with id:"+id+"!", HttpStatus.BAD_REQUEST);

        teacher.get().setAge(userRegisterRequest.getAge());
        teacher.get().setPassword(userRegisterRequest.getPassword());
        teacher.get().setFirstName(userRegisterRequest.getFirstName());
        teacher.get().setLastName(userRegisterRequest.getLastName());
        teacherRepository.save(teacher.get());
    }
}
