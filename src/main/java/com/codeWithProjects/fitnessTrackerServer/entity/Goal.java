package com.codeWithProjects.fitnessTrackerServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean achieved;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}