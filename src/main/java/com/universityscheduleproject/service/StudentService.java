package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.student.StudentRequestDTO;
import com.universityscheduleproject.dto.student.StudentResponseDTO;
import com.universityscheduleproject.entity.Student;
import com.universityscheduleproject.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public StudentResponseDTO registerStudent(StudentRequestDTO studentRequestDTO) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentRequestDTO.getEmail());
        if (optionalStudent.isPresent()) {
            throw new RuntimeException("Student already exists!");
        }

        Student student = new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setMajor(studentRequestDTO.getMajor());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        studentRepository.save(student);

        return StudentResponseDTO.fromEntity(student);
    }

    public StudentResponseDTO getByStudentId(String studentId) {
        Optional<Student> optionalStudent = studentRepository.getStudentByStudentId(studentId);
        if (optionalStudent.isPresent()) {
            return StudentResponseDTO.fromEntity(optionalStudent.get());
        }
        throw new RuntimeException("Student not found!");
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<StudentResponseDTO> students = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            StudentResponseDTO dto = StudentResponseDTO.fromEntity(student);
            students.add(dto);
        }
        return students;
    }
}
