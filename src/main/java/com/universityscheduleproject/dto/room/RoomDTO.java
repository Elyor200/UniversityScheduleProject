package com.universityscheduleproject.dto.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityscheduleproject.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO {
    private Long roomId;
    private String roomNumber;
    private String floor;
    private Integer capacity;

    public RoomDTO(@JsonProperty("room_id") Long roomId,
                   @JsonProperty("room_number") String roomNumber,
                   @JsonProperty("floor") String floor,
                   @JsonProperty("capacity") Integer capacity) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
    }

    public static RoomDTO fromEntity(Room room) {
        RoomDTO  roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setFloor(room.getFloor());
        roomDTO.setCapacity(room.getCapacity());
        return roomDTO;
    }
}
