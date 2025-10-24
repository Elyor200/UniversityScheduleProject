package com.universityscheduleproject.entity;

import com.universityscheduleproject.dto.room.RoomDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "floor")
    private String floor;
    @Column(name = "capacity")
    private Integer capacity;

    public static Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setFloor(roomDTO.getFloor());
        room.setCapacity(roomDTO.getCapacity());
        return room;
    }
}
