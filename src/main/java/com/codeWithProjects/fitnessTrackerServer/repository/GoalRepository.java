package com.codeWithProjects.fitnessTrackerServer.repository;
import com.codeWithProjects.fitnessTrackerServer.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
}