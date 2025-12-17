package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.ScheduleRequestDTO;
import com.universityscheduleproject.dto.ScheduleResponseDTO;
import com.universityscheduleproject.dto.course.CourseDTO;
import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.entity.*;
import com.universityscheduleproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;
    private RoomRepository roomRepository;
    private EnrollmentRepository enrollmentRepository;

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

    public List<ScheduleResponseDTO> getAllSchedulesByProfessorId(Long id) {
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findByProfessorId(id);
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDTO responseDTO = ScheduleResponseDTO.fromEntity(schedule);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<ScheduleResponseDTO> getAllSchedulesByCourseId(Long id) {
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findByCourseId(id);
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDTO responseDTO = ScheduleResponseDTO.fromEntity(schedule);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<ScheduleResponseDTO> getAllSchedulesByRoomId(Long id) {
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findByRoomId(id);
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDTO responseDTO = ScheduleResponseDTO.fromEntity(schedule);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<ScheduleResponseDTO> getAllSchedulesByDayOfWeek(String dayOfWeek) {
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findByDayOfWeek(dayOfWeek);
        for (Schedule schedule : scheduleList) {
            ScheduleResponseDTO responseDTO = ScheduleResponseDTO.fromEntity(schedule);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<ScheduleResponseDTO> getAllSchedulesByStudentId(Long studentId) {
//        List<Schedule> allSchedulesByStudentId = scheduleRepository.getAllSchedulesByStudentId(studentId);
        List<Enrollment> enrollmentList = enrollmentRepository.findByStudentId(studentId);
        List<ScheduleResponseDTO> responseDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            Schedule schedule = enrollment.getSchedule();
            ScheduleResponseDTO responseDTO = ScheduleResponseDTO.fromEntity(schedule);
            responseDTO.setEnrolledAt(enrollment.getEnrolledAt());
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public boolean deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found!"));
        scheduleRepository.delete(schedule);
        return true;
    }
}
