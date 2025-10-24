package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.dto.room.RoomRequestDTO;
import com.universityscheduleproject.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/room")
public class RoomController {
    private RoomService roomService;

    @PostMapping("/create")
    private ResponseEntity<?> createRoom(@RequestBody RoomRequestDTO requestDTO) {
        RoomDTO room = roomService.createRoom(requestDTO);
        return ResponseEntity.ok(room);
    }
}
