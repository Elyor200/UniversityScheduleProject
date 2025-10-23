package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
