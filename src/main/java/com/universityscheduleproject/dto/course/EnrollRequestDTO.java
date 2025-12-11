package com.universityscheduleproject.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollRequestDTO {
    private String studentId;
    private String email;
    private String courseName;
}
