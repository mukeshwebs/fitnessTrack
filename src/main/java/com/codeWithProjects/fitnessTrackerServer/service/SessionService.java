package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.SessionDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Session;
import com.codeWithProjects.fitnessTrackerServer.repository.ProgramRepository;
import com.codeWithProjects.fitnessTrackerServer.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public SessionDTO createSession(SessionDTO dto) {
        Session session = new Session();
        mapDtoToEntity(dto, session);
        session.setProgram(programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program not found")));
        session.setBookedSlots(0);
        return toDto(sessionRepository.save(session));
    }

    public List<SessionDTO> getSessionsByProgram(Long programId) {
        return sessionRepository.findByProgramId(programId)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    private SessionDTO toDto(Session session) {
        SessionDTO dto = new SessionDTO();
        dto.setId(session.getId());
        dto.setTitle(session.getTitle());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setCapacity(session.getCapacity());
        dto.setBookedSlots(session.getBookedSlots());
        dto.setProgramId(session.getProgram().getId());
        return dto;
    }

    private void mapDtoToEntity(SessionDTO dto, Session session) {
        session.setTitle(dto.getTitle());
        session.setStartTime(dto.getStartTime());
        session.setEndTime(dto.getEndTime());
        session.setCapacity(dto.getCapacity());
    }
}