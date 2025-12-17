package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    Optional<Student> getStudentById(Long id);

    Optional<Student> getStudentByStudentId(String studentId);

    @Query(value = """
                SELECT DISTINCT st.*
                FROM enrollments e
                INNER JOIN schedule sch ON e.schedule_id = sch.id
                INNER JOIN course c ON sch.course_id = c.id
                INNER JOIN professor p ON c.professor_id = p.id
                INNER JOIN student st ON e.student_id = st.id
                WHERE p.id = :professorId
            """, nativeQuery = true)
    List<Student> getAllStudentsByProfessorId(@Param("professorId") Long professorId);

}
