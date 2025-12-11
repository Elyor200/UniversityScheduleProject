package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);

    List<Course> getAllByProfessorIdAndProfessorEmail(Long professorId, String email);

    @Query("SELECT s.courses FROM Student s WHERE s.studentId = :studentId")
    List<Course> getAllCoursesByStudentId(@Param("studentId") String studentId);
}
