package com.universityscheduleproject.dto.course;

import com.universityscheduleproject.dto.student.StudentResponseDTO;
import com.universityscheduleproject.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollResponseDTO {
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Integer creditHours;
    private StudentResponseDTO student;

    public static EnrollResponseDTO fromEntity(Course course) {
        EnrollResponseDTO responseDTO = new EnrollResponseDTO();
        responseDTO.setCourseId(course.getId());
        responseDTO.setCourseName(course.getCourseName());
        responseDTO.setCourseCode(course.getCourseCode());
        responseDTO.setCreditHours(course.getCreditHours());
        return responseDTO;
    }
}
