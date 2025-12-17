package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.course.*;
import com.universityscheduleproject.dto.student.StudentResponseDTO;
import com.universityscheduleproject.entity.*;
import com.universityscheduleproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.core.ReactiveAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private final ScheduleRepository scheduleRepository;
    private final EnrollmentRepository enrollmentRepository;
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

    public ResponseMessage enrollToCourse(EnrollRequestDTO requestDTO) {
        Student student = studentRepository.getStudentById(requestDTO.getStudentId()).orElseThrow(() ->
                new IllegalStateException("Student does not exist"));

        Schedule schedule = scheduleRepository.findById(requestDTO.getScheduleId()).orElseThrow(() ->
                new IllegalStateException("Schedule does not exist"));

        if (enrollmentRepository.existsByStudentAndSchedule(student, schedule)) {
            throw new RuntimeException("Student already enrolled in this schedule");
        }

        List<Enrollment> byStudentId = enrollmentRepository.findByStudentId(requestDTO.getStudentId());
        for (Enrollment enrollment : byStudentId) {
            Schedule s = enrollment.getSchedule();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

            LocalTime existingStart = LocalTime.parse(s.getStartTime(), formatter);
            LocalTime existingEnd   = LocalTime.parse(s.getEndTime(), formatter);
            LocalTime newStart      = LocalTime.parse(schedule.getStartTime(), formatter);
            LocalTime newEnd        = LocalTime.parse(schedule.getEndTime(), formatter);

            boolean timeOverlap = existingStart.isBefore(newEnd) && existingEnd.isAfter(newStart);

            if (s.getDayOfWeek().equals(schedule.getDayOfWeek()) && timeOverlap) {
                throw new RuntimeException("Schedule conflict with another enrolled course");
            }

            if (newEnd.isBefore(newStart) || newEnd.equals(newStart)) {
                throw new IllegalArgumentException("End time must be after start time");
            }
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setSchedule(schedule);
        enrollment.setStudent(student);
        enrollment.setCourse(schedule.getCourse());
        enrollmentRepository.save(enrollment);

        return new ResponseMessage("Enroll success");
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

    public List<CourseDTO> getAllCoursesByStudentId(Long studentId) {
        List<Enrollment> allCoursesByStudentId = enrollmentRepository.getAllEnrollmentsByStudentId(studentId);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Enrollment enrollment : allCoursesByStudentId) {
            Course course = enrollment.getCourse();
            Schedule schedule = enrollment.getSchedule();

            CourseDTO courseDTO = CourseDTO.fromEntity(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
