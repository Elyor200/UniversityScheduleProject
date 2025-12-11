package com.universityscheduleproject.entity;

import com.universityscheduleproject.dto.ScheduleResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;
    @Column(name = "start_time", nullable = false)
    private String startTime;
    @Column(name = "end_time", nullable = false)
    private String endTime;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    public static Schedule toEntity(ScheduleResponseDTO responseDTO) {
        Schedule schedule = new Schedule();
        schedule.setDayOfWeek(responseDTO.getDayOfWeek());
        schedule.setStartTime(responseDTO.getStartTime());
        schedule.setEndTime(responseDTO.getEndTime());
        schedule.setCourse(Course.toEntity(responseDTO.getCourseDTO()));
        schedule.setRoom(Room.toEntity(responseDTO.getRoomDTO()));
        schedule.setProfessor(Professor.toEntity(responseDTO.getProfessorDTO()));
        return schedule;
    }
}
