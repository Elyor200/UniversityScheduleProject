package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.course.CourseRequestDTO;
import com.universityscheduleproject.dto.course.EnrollRequestDTO;
import com.universityscheduleproject.dto.course.ResponseMessage;
import com.universityscheduleproject.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        List<CourseDTO> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping("/getCourseById")
    public ResponseEntity<?> getCourseById(@RequestParam Long courseId) {
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/getAllCoursesByProfessorIdAndEmail")
    public ResponseEntity<?> getAllCoursesByProfessorIdAndEmail(@RequestParam Long professorId,
                                                                @RequestParam String email) {
        return ResponseEntity.ok(courseService.getAllCourseByProfessorIdAndEmail(professorId, email));
    }

    @PostMapping("/enroll-to-course")
    public ResponseEntity<ResponseMessage> enrollToCourse(@RequestBody EnrollRequestDTO enrollRequestDTO) {
        ResponseMessage responseMessage = courseService.enrollToCourse(enrollRequestDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/getEnrolledCoursesByStudentId")
    public ResponseEntity<?> getEnrolledCoursesByStudentId(@RequestParam Long studentId) {
        return ResponseEntity.ok(courseService.getAllCoursesByStudentId(studentId));
    }
}
