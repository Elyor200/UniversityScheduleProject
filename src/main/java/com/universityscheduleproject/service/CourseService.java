package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.course.CourseRequestDTO;
import com.universityscheduleproject.dto.course.EnrollRequestDTO;
import com.universityscheduleproject.dto.course.EnrollResponseDTO;
import com.universityscheduleproject.dto.student.StudentResponseDTO;
import com.universityscheduleproject.entity.Course;
import com.universityscheduleproject.entity.Professor;
import com.universityscheduleproject.entity.Student;
import com.universityscheduleproject.repository.CourseRepository;
import com.universityscheduleproject.repository.ProfessorRepository;
import com.universityscheduleproject.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;

    public CourseDTO createCourse(CourseRequestDTO requestDTO) {
        Optional<Course> optionalCourse = courseRepository.findByCourseName(requestDTO.getCourseName());
        if (optionalCourse.isPresent()) {
            throw new IllegalStateException("Course already exists");
        }
        Professor professor = professorRepository.findByEmail(requestDTO.getProfessor().getEmail())
                .orElseThrow(() -> new IllegalStateException("Professor does not exist"));

        Course course = new Course();
        course.setCourseCode(requestDTO.getCourseCode());
        course.setCreditHours(requestDTO.getCreditHours());
        course.setCourseName(requestDTO.getCourseName());
        course.setProfessor(professor);
        courseRepository.save(course);

        return CourseDTO.fromEntity(course);
    }

    public EnrollResponseDTO enrollToCourse(EnrollRequestDTO requestDTO) {
        Student student = studentRepository.getStudentByStudentId(requestDTO.getStudentId()).orElseThrow(() ->
                new IllegalStateException("Student does not exist"));
        Course course = courseRepository.findByCourseName(requestDTO.getCourseName()).orElseThrow(() ->
                new IllegalStateException("Course does not exist"));


        if (student.getCourses().contains(course)) {
            throw new IllegalStateException("Student already enrolled in the course");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);

        EnrollResponseDTO responseDTO = EnrollResponseDTO.fromEntity(course);
        StudentResponseDTO studentResponseDTO = StudentResponseDTO.fromEntity(student);
        responseDTO.setStudent(studentResponseDTO);

        return responseDTO;
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> courseList = courseRepository.findAll();
        for (Course course : courseList) {
            CourseDTO courseDTO = CourseDTO.fromEntity(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public CourseDTO getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) {
            throw new RuntimeException("Course not found!");
        }
        return CourseDTO.fromEntity(optionalCourse.get());
    }

    public List<CourseDTO> getAllCourseByProfessorIdAndEmail(Long professorId, String email) {
        List<Course> allByProfessorIdAndProfessorEmail = courseRepository.getAllByProfessorIdAndProfessorEmail(professorId, email);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course: allByProfessorIdAndProfessorEmail) {
            CourseDTO courseDTO = CourseDTO.fromEntity(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public List<CourseDTO> getAllCoursesByStudentId(String studentId) {
        List<Course> allCoursesByStudentId = courseRepository.getAllCoursesByStudentId(studentId);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course: allCoursesByStudentId) {
            CourseDTO courseDTO = CourseDTO.fromEntity(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
