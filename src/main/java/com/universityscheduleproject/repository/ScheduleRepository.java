package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByRoomIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long roomId, String dayOfWeek, String startTime, String endTime);

    boolean existsByProfessorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Long professorId, String dayOfWeek, String startTime, String endTIme);

    List<Schedule> findByProfessorId(Long professorId);
    List<Schedule> findByCourseId(Long courseId);
    List<Schedule> findByRoomId(Long roomId);
    List<Schedule> findByDayOfWeek(String dayOfWeek);
}
