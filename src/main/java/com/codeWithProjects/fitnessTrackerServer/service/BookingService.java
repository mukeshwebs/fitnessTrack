package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.BookingDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Session;
import com.codeWithProjects.fitnessTrackerServer.entity.SessionBooking;
import com.codeWithProjects.fitnessTrackerServer.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SessionBookingRepository bookingRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;

    @Transactional
    public BookingDTO createBooking(BookingDTO dto) {
        Session session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));
                
        if (session.getBookedSlots() >= session.getCapacity()) {
            throw new IllegalStateException("Session is fully booked");
        }
        
        SessionBooking booking = new SessionBooking();
        booking.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        booking.setSession(session);
        booking.setBookingTime(dto.getBookingTime());
        
        String paymentStatus = paymentService.processPayment(dto);
        booking.setPaymentStatus(paymentStatus);
        
        session.setBookedSlots(session.getBookedSlots() + 1);
        sessionRepository.save(session);
        
        return toDto(bookingRepository.save(booking));
    }

    public List<BookingDTO> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    private BookingDTO toDto(SessionBooking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setSessionId(booking.getSession().getId());
        dto.setBookingTime(booking.getBookingTime());
        dto.setPaymentStatus(booking.getPaymentStatus());
        return dto;
    }
}