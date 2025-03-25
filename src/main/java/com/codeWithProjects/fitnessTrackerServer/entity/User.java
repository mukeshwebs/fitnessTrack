package com.codeWithProjects.fitnessTrackerServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    private String role; // ADMIN, TRAINER, USER
    
    @OneToMany(mappedBy = "user")
    private List<Program> programs;
    
    @OneToMany(mappedBy = "user")
    private List<Goal> goals;
    
    @OneToMany(mappedBy = "user")
    private List<SessionBooking> bookings;
}