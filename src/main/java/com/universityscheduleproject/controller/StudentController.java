package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.student.StudentRequestDTO;
import com.universityscheduleproject.dto.student.StudentResponseDTO;
import com.universityscheduleproject.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/student")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRequestDTO requestDTO) {
        StudentResponseDTO responseDTO = studentService.registerStudent(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/getByStudentId")
    public ResponseEntity<?> getByStudentId(@RequestParam String studentId) {
        StudentResponseDTO studentById = studentService.getByStudentId(studentId);
        return ResponseEntity.ok(studentById);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
