package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NutritionLogDTO {
    private Long id;
    private Long userId;
    private String mealDescription;
    private Integer calories;
    private LocalDateTime logTime;
}