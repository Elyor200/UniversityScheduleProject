package com.universityscheduleproject.repository;

import com.universityscheduleproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
