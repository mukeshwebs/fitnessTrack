package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.GoalDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Goal;
import com.codeWithProjects.fitnessTrackerServer.repository.GoalRepository;
import com.codeWithProjects.fitnessTrackerServer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Transactional
    public GoalDTO postGoal(GoalDTO dto) {
        Goal goal = new Goal();
        goal.setDescription(dto.getDescription());
        goal.setStartDate(dto.getStartDate());
        goal.setEndDate(dto.getEndDate());
        goal.setAchieved(false);
        goal.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        return toDto(goalRepository.save(goal));
    }

    public List<GoalDTO> getGoals(Long userId) {
        return goalRepository.findByUserId(userId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public GoalDTO updateStatus(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
        goal.setAchieved(true);
        return toDto(goalRepository.save(goal));
    }

    private GoalDTO toDto(Goal goal) {
        GoalDTO dto = new GoalDTO();
        dto.setId(goal.getId());
        dto.setDescription(goal.getDescription());
        dto.setStartDate(goal.getStartDate());
        dto.setEndDate(goal.getEndDate());
        dto.setAchieved(goal.isAchieved());
        dto.setUserId(goal.getUser().getId());
        return dto;
    }
}