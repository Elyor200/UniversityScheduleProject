package com.universityscheduleproject.dto.course;

import com.universityscheduleproject.dto.professor.ProfessorRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    private String courseName;
    private String courseCode;
    private Integer creditHours;
    private ProfessorRequestDTO professor;
}
