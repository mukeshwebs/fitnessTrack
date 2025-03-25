package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.NutritionLogDTO;
import com.codeWithProjects.fitnessTrackerServer.service.NutritionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NutritionController {
    private final NutritionService nutritionService;

    @PostMapping
    public ResponseEntity<NutritionLogDTO> logNutrition(@Valid @RequestBody NutritionLogDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nutritionService.logNutrition(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NutritionLogDTO>> getUserNutritionLogs(@PathVariable Long userId) {
        return ResponseEntity.ok(nutritionService.getUserNutritionLogs(userId));
    }
}