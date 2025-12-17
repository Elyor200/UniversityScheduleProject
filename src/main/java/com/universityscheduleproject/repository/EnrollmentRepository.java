package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Enrollment;
import com.universityscheduleproject.entity.Schedule;
import com.universityscheduleproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndSchedule(Student student, Schedule schedule);

    List<Enrollment> findByStudentId(Long studentId);

    @Query("SELECT e FROM Enrollment e " +
            "JOIN e.course " +
            "JOIN e.schedule " +
            "WHERE e.student.id = :studentId")
    List<Enrollment> getAllEnrollmentsByStudentId(@Param("studentId") Long studentId);
}
