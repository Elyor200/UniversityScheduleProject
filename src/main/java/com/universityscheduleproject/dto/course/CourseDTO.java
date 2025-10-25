package com.universityscheduleproject.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Integer creditHours;
    private ProfessorDTO professorDTO;

    public CourseDTO(@JsonProperty("course_id") Long courseId,
                     @JsonProperty("course_name") String courseName,
                     @JsonProperty("course_code") String courseCode,
                     @JsonProperty("credit_hours") Integer creditHours,
                     @JsonProperty("professor") ProfessorDTO professorDTO) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHours = creditHours;
        this.professorDTO = professorDTO;
    }

    public static CourseDTO fromEntity(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseCode(course.getCourseCode());
        courseDTO.setCreditHours(course.getCreditHours());
        courseDTO.setProfessorDTO(ProfessorDTO.fromEntity(course.getProfessor()));
        return courseDTO;
    }
}
