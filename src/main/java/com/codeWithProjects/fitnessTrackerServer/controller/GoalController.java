package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.GoalDTO;
import com.codeWithProjects.fitnessTrackerServer.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GoalController {
    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalDTO> postGoal(@Valid @RequestBody GoalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(goalService.postGoal(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GoalDTO>> getGoals(@PathVariable Long userId) {
        return ResponseEntity.ok(goalService.getGoals(userId));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<GoalDTO> updateStatus(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.updateStatus(id));
    }
}