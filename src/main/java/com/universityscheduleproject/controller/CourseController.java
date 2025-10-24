package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.course.CourseRequestDTO;
import com.universityscheduleproject.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/course")
public class CourseController {
    private CourseService courseService;

    @PostMapping("/create-course")
    private ResponseEntity<?> createCourse(@RequestBody CourseRequestDTO requestDTO) {
        try {
            CourseDTO course = courseService.createCourse(requestDTO);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
