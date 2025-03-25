package com.codeWithProjects.fitnessTrackerServer.repository;
import com.codeWithProjects.fitnessTrackerServer.entity.SessionBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SessionBookingRepository extends JpaRepository<SessionBooking, Long> {
    List<SessionBooking> findByUserId(Long userId);
}