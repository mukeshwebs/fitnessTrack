package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.ProgramDTO;
import com.codeWithProjects.fitnessTrackerServer.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@Valid @RequestBody ProgramDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programService.createProgram(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(programService.getAllPrograms(category, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgram(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgram(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramDTO> updateProgram(@PathVariable Long id,
                                                  @Valid @RequestBody ProgramDTO dto) {
        return ResponseEntity.ok(programService.updateProgram(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}