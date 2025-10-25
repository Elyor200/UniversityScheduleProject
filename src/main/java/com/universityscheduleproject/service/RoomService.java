package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.room.RoomDTO;
import com.universityscheduleproject.dto.room.RoomRequestDTO;
import com.universityscheduleproject.entity.Room;
import com.universityscheduleproject.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;

import java.util.ArrayList;
import java.util.List;
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

    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            RoomDTO roomDTO = RoomDTO.fromEntity(room);
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    public RoomDTO getRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) {
            throw new RuntimeException("Room does not exist");
        }
        return RoomDTO.fromEntity(optionalRoom.get());
    }
}
