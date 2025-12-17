package com.universityscheduleproject.dto;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDTO {
    private Long id;
    private String name;
    private String description;
    private CourseDTO courseDTO;
    private ProfessorDTO professorDTO;
    private RoomDTO roomDTO;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private LocalDateTime enrolledAt;

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public static ScheduleResponseDTO fromEntity(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setId(schedule.getId());
        dto.setCourseDTO(CourseDTO.fromEntity(schedule.getCourse()));
        dto.setProfessorDTO(ProfessorDTO.fromEntity(schedule.getProfessor()));
        dto.setRoomDTO(RoomDTO.fromEntity(schedule.getRoom()));
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        return dto;
    }
}
