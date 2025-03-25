package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;
import java.util.List;

@Data
public class CommunityGroupDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> memberIds;
}