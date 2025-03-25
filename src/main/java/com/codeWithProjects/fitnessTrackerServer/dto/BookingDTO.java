package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long sessionId;
    private LocalDateTime bookingTime;
    private String paymentStatus;
}