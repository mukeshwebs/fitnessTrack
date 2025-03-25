package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.SessionDTO;
import com.codeWithProjects.fitnessTrackerServer.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@Valid @RequestBody SessionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessionService.createSession(dto));
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<List<SessionDTO>> getSessionsByProgram(@PathVariable Long programId) {
        return ResponseEntity.ok(sessionService.getSessionsByProgram(programId));
    }
}