package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.course.CourseRequestDTO;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.entity.Course;
import com.universityscheduleproject.entity.Professor;
import com.universityscheduleproject.repository.CourseRepository;
import com.universityscheduleproject.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
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
}
