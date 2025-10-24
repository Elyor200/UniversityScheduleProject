package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);

    Optional<Professor> findByEmailAndFirstNameAndLastName(String email, String firstName, String lastName);

}
