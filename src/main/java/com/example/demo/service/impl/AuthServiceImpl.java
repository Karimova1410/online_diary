package com.example.demo.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthLoginResponse;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.BadCredentialsException;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private PasswordEncoder encoder;
    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent())
            throw new BadCredentialsException("user with email: "+userRegisterRequest.getEmail()+" is already exist!");
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole(Role.valueOf(userRegisterRequest.getRole().toUpperCase()));
        user.setPassword(encoder.encode(userRegisterRequest.getPassword()));

        if (Role.STUDENT.equals(user.getRole())) {
            Student student = new Student();
            student.setFirstName(userRegisterRequest.getFirstName());
            student.setLastName(userRegisterRequest.getLastName());
            student.setEmail(userRegisterRequest.getEmail());
            student.setPassword(userRegisterRequest.getPassword());
            student.setAge(userRegisterRequest.getAge());
            student.setUser(user);
            student.setRole(user.getRole());
            studentRepository.save(student);
            user.setStudent(student);
            userRepository.save(user);

        } else if (Role.TEACHER.equals(user.getRole())) {
            Teacher teacher = new Teacher();
            teacher.setFirstName(userRegisterRequest.getFirstName());
            teacher.setLastName(userRegisterRequest.getLastName());
            teacher.setEmail(userRegisterRequest.getEmail());
            teacher.setPassword(userRegisterRequest.getPassword());
            teacher.setAge(userRegisterRequest.getAge());
            teacher.setUser(user);
            teacher.setRole(user.getRole());
            teacherRepository.save(teacher);
            user.setTeacher(teacher);
            userRepository.save(user);

        }

    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        Optional<User> user = userRepository.findByEmail(authLoginRequest.getEmail());
        if (user.isEmpty())
            throw new BadCredentialsException("user not found");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getEmail(),authLoginRequest.getPassword()));

        }catch (org.springframework.security.authentication.BadCredentialsException e){
            throw new BadCredentialsException("user not found..");
        }


        return convertToResponse(user);
    }
    private AuthLoginResponse convertToResponse(Optional<User> user) {
        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());
        if (user.get().getRole().equals(Role.STUDENT))
            authLoginResponse.setName(user.get().getStudent().getFirstName());
        if (user.get().getRole().equals(Role.TEACHER))
            authLoginResponse.setName(user.get().getTeacher().getFirstName());
        Map<String, Object> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user.get());
        authLoginResponse.setToken(token);

        return authLoginResponse;
    }

    @Override
    public User getUsernameFromToken(String token){

        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null"));
    }
}
