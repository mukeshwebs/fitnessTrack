package com.codeWithProjects.fitnessTrackerServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "session_bookings")
public class SessionBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
    
    private LocalDateTime bookingTime;
    private String paymentStatus;
}