package com.codeWithProjects.fitnessTrackerServer.repository;
import com.codeWithProjects.fitnessTrackerServer.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByProgramId(Long programId);
}