package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Schedule;
import com.universityscheduleproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByRoomIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long roomId, String dayOfWeek, String startTime, String endTime);

    boolean existsByProfessorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long professorId, String dayOfWeek, String startTime, String endTIme);

    List<Schedule> findByProfessorId(Long professorId);
    List<Schedule> findByCourseId(Long courseId);
    List<Schedule> findByRoomId(Long roomId);
    List<Schedule> findByDayOfWeek(String dayOfWeek);

    @Query(value = "select s.* from schedule s " +
            "join enrollments e on s.id = e.schedule_id " +
            "where e.student_id = :studentId",
            nativeQuery = true)
    List<Schedule> getAllSchedulesByStudentId(@Param("studentId") Long studentId);
}
