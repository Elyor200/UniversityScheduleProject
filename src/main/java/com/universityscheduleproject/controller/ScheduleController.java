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
}
