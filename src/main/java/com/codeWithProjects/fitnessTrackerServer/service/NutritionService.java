package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.NutritionLogDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.NutritionLog;
import com.codeWithProjects.fitnessTrackerServer.repository.NutritionLogRepository;
import com.codeWithProjects.fitnessTrackerServer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutritionService {
    private final NutritionLogRepository nutritionLogRepository;
    private final UserRepository userRepository;

    @Transactional
    public NutritionLogDTO logNutrition(NutritionLogDTO dto) {
        NutritionLog log = new NutritionLog();
        log.setMealDescription(dto.getMealDescription());
        log.setCalories(dto.getCalories());
        log.setLogTime(dto.getLogTime());
        log.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        
        return toDto(nutritionLogRepository.save(log));
    }

    public List<NutritionLogDTO> getUserNutritionLogs(Long userId) {
        return nutritionLogRepository.findByUserId(userId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    private NutritionLogDTO toDto(NutritionLog log) {
        NutritionLogDTO dto = new NutritionLogDTO();
        dto.setId(log.getId());
        dto.setUserId(log.getUser().getId());
        dto.setMealDescription(log.getMealDescription());
        dto.setCalories(log.getCalories());
        dto.setLogTime(log.getLogTime());
        return dto;
    }
}