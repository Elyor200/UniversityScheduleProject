package com.universityscheduleproject.dto;

import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDTO {
    private CourseDTO courseDTO;
    private ProfessorDTO professorDTO;
    private RoomDTO roomDTO;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public static ScheduleResponseDTO fromEntity(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setCourseDTO(CourseDTO.fromEntity(schedule.getCourse()));
        dto.setProfessorDTO(ProfessorDTO.fromEntity(schedule.getProfessor()));
        dto.setRoomDTO(RoomDTO.fromEntity(schedule.getRoom()));
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        return dto;
    }
}
