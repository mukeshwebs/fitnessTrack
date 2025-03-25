package com.codeWithProjects.fitnessTrackerServer.repository;
import com.codeWithProjects.fitnessTrackerServer.entity.NutritionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NutritionLogRepository extends JpaRepository<NutritionLog, Long> {
    List<NutritionLog> findByUserId(Long userId);
}