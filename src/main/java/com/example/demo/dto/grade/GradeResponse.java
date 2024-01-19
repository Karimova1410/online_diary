package com.example.demo.dto.grade;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GradeResponse {
    private Long id;
    private Integer score;
    private String subject;
    private String theme;
    private LocalDate date;
}
