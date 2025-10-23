package com.universityscheduleproject.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
