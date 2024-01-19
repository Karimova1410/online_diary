package com.example.demo.dto.grade;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Locale;

@Getter
@Setter
public class GradeRequest {
    private Integer score;
    private String subject;
    private String theme;
    private LocalDate date;

}
