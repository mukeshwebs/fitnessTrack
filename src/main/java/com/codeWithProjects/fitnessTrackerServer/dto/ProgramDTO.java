package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProgramDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Long userId;
    private List<SessionDTO> sessions;
}