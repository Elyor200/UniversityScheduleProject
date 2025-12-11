package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.ScheduleRequestDTO;
import com.universityscheduleproject.dto.ScheduleResponseDTO;
import com.universityscheduleproject.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    @PostMapping("/create-schedule")
    private ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDTO requestDTO) {
        try {
            ScheduleResponseDTO schedule = scheduleService.createSchedule(requestDTO);
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/getAllSchedules")
    private ResponseEntity<?> getAllSchedules() {
        List<ScheduleResponseDTO> allSchedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(allSchedules);
    }

    @GetMapping("/getAllSchedulesByProfessorId")
    private ResponseEntity<?> getAllSchedulesByProfessorId(@RequestParam Long professorId) {
        return ResponseEntity.ok(scheduleService.getAllSchedulesByProfessorId(professorId));
    }

    @GetMapping("/getAllSchedulesByCourseId")
    private ResponseEntity<?> getAllSchedulesByCourseId(@RequestParam("courseId") Long courseId) {
        return ResponseEntity.ok(scheduleService.getAllSchedulesByCourseId(courseId));
    }

    @GetMapping("/getAllSchedulesByRoomId")
    private ResponseEntity<?> getAllSchedulesByRoomId(@RequestParam("roomId") Long roomId) {
        return ResponseEntity.ok(scheduleService.getAllSchedulesByRoomId(roomId));
    }

    @GetMapping("/getAllSchedulesByDayOfWeek")
    private ResponseEntity<?> getAllSchedulesByDayOfWeek(@RequestParam("dayOfWeek") String dayOfWeek) {
        return ResponseEntity.ok(scheduleService.getAllSchedulesByDayOfWeek(dayOfWeek));
    }

    @GetMapping("/getAllSchedulesByStudentId")
    private ResponseEntity<?> getAllSchedulesByStudentId(@RequestParam("studentId") Long studentId) {
        return ResponseEntity.ok(scheduleService.getAllSchedulesByStudentId(studentId));
    }

    @DeleteMapping("/deleteScheduleById")
    private ResponseEntity<?> deleteScheduleById(@RequestParam("id") Long id) {
        boolean b = scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(b);
    }
}
