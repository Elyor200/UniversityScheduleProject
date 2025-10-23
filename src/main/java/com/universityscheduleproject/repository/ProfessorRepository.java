package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
