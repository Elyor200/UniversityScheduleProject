package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);

    Optional<Professor> findByEmailAndFirstNameAndLastName(String email, String firstName, String lastName);

    @Query(value = "select p.* from enrollments e " +
            "inner join course c on e.course_id = c.id " +
            "inner join professor p on c.professor_id = p.id " +
            "where e.student_id = :studentId",
            nativeQuery = true)
    List<Professor> getAllProfessorsByStudentId(@Param("studentId") Long studentId);

}
