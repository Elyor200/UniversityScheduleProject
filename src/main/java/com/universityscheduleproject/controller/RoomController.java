package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.dto.room.RoomRequestDTO;
import com.universityscheduleproject.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllRooms")
    public ResponseEntity<?> getAllRoom() {
        List<RoomDTO> allRooms = roomService.getAllRooms();
        return ResponseEntity.ok(allRooms);
    }

    @GetMapping("/getRoomById")
    public ResponseEntity<?> getRoomById(Long id) {
        RoomDTO room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }
}
