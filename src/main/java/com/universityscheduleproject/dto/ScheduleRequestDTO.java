package com.universityscheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {
    private Long courseId;
    private Long professorId;
    private Long roomId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
