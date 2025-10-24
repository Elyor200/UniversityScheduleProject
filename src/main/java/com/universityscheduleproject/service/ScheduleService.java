package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.ScheduleRequestDTO;
import com.universityscheduleproject.dto.ScheduleResponseDTO;
import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.entity.Course;
import com.universityscheduleproject.entity.Professor;
import com.universityscheduleproject.entity.Room;
import com.universityscheduleproject.entity.Schedule;
import com.universityscheduleproject.repository.CourseRepository;
import com.universityscheduleproject.repository.ProfessorRepository;
import com.universityscheduleproject.repository.RoomRepository;
import com.universityscheduleproject.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.universityscheduleproject.entity.Schedule.toEntity;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;
    private RoomRepository roomRepository;

    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found!"));
        Professor professor = professorRepository.findById(requestDTO.getProfessorId())
                .orElseThrow(() ->  new RuntimeException("Professor not found!"));
        Room room = roomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() ->  new RuntimeException("Room not found!"));

        if (scheduleRepository.existsByRoomIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual
                (requestDTO.getRoomId(), requestDTO.getDayOfWeek(), requestDTO.getStartTime(), requestDTO.getEndTime())) {
            throw new RuntimeException("Room is already booked at this time!");
        }
        if (scheduleRepository.existsByProfessorIdAndDayOfWeekAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual
                (requestDTO.getProfessorId(),  requestDTO.getDayOfWeek(), requestDTO.getStartTime(), requestDTO.getEndTime())) {
            throw new RuntimeException("Professor is already booked at this time!");
        }

        Schedule schedule = new Schedule();
        schedule.setRoom(room);
        schedule.setProfessor(professor);
        schedule.setDayOfWeek(requestDTO.getDayOfWeek());
        schedule.setStartTime(requestDTO.getStartTime());
        schedule.setEndTime(requestDTO.getEndTime());
        schedule.setCourse(course);

        scheduleRepository.save(schedule);
        return ScheduleResponseDTO.fromEntity(schedule);
    }

    public List<ScheduleResponseDTO> getAllSchedules() {
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findAll();
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDTO responseDTO = new ScheduleResponseDTO();
            responseDTO.setCourseDTO(CourseDTO.fromEntity(schedule.getCourse()));
            responseDTO.setRoomDTO(RoomDTO.fromEntity(schedule.getRoom()));
            responseDTO.setProfessorDTO(ProfessorDTO.fromEntity(schedule.getProfessor()));
            responseDTO.setStartTime(schedule.getStartTime());
            responseDTO.setEndTime(schedule.getEndTime());
            responseDTO.setDayOfWeek(schedule.getDayOfWeek());
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }
}
