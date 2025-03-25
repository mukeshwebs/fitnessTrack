package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.ProgramDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Program;
import com.codeWithProjects.fitnessTrackerServer.repository.ProgramRepository;
import com.codeWithProjects.fitnessTrackerServer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProgramDTO createProgram(ProgramDTO dto) {
        Program program = new Program();
        mapDtoToEntity(dto, program);
        program.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        return toDto(programRepository.save(program));
    }

    public List<ProgramDTO> getAllPrograms(String category, String search) {
        if (category != null && search != null) {
            return programRepository.findByCategoryAndNameContaining(category, search)
                    .stream().map(this::toDto).collect(Collectors.toList());
        }
        return programRepository.findAll()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProgramDTO getProgram(Long id) {
        return toDto(programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found")));
    }

    @Transactional
    public ProgramDTO updateProgram(Long id, ProgramDTO dto) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));
        mapDtoToEntity(dto, program);
        return toDto(programRepository.save(program));
    }

    @Transactional
    public void deleteProgram(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));
        programRepository.delete(program);
    }

    private ProgramDTO toDto(Program program) {
        ProgramDTO dto = new ProgramDTO();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setDescription(program.getDescription());
        dto.setCategory(program.getCategory());
        dto.setUserId(program.getUser().getId());
        return dto;
    }

    private void mapDtoToEntity(ProgramDTO dto, Program program) {
        program.setName(dto.getName());
        program.setDescription(dto.getDescription());
        program.setCategory(dto.getCategory());
    }
}