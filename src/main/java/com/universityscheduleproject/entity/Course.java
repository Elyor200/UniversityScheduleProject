package com.universityscheduleproject.entity;

import com.universityscheduleproject.dto.course.CourseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "credit_hours")
    private Integer creditHours;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Schedule> schedules;

    public static Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCreditHours(courseDTO.getCreditHours());
        course.setProfessor(Professor.toEntity(courseDTO.getProfessorDTO()));
        return course;
    }
}
