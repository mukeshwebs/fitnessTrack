package com.codeWithProjects.fitnessTrackerServer.repository;
import com.codeWithProjects.fitnessTrackerServer.entity.CommunityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityGroupRepository extends JpaRepository<CommunityGroup, Long> {
}