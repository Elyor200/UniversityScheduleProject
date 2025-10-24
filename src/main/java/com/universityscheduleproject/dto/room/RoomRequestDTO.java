package com.universityscheduleproject.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    private String roomNumber;
    private String floor;
    private Integer capacity;
}
