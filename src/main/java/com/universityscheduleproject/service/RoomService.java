package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.dto.room.RoomRequestDTO;
import com.universityscheduleproject.entity.Room;
import com.universityscheduleproject.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {
    private final RouterFunctionMapping routerFunctionMapping;
    private RoomRepository roomRepository;

    public RoomDTO createRoom(RoomRequestDTO request) {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumberAndCapacityAndFloor
                (request.getRoomNumber(), request.getCapacity(), request.getFloor());
        if (optionalRoom.isPresent()) {
            throw new RuntimeException("Room already exists");
        }
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setFloor(request.getFloor());
        roomRepository.save(room);
        return RoomDTO.fromEntity(room);
    }
}
