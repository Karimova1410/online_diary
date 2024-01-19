package com.example.demo.repositories;

import com.example.demo.entities.Student;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    Optional<Object> findByEmail(String childsEmail);

    Optional<Student> findByUser(User user);
}
