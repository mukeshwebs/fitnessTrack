package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.CommunityGroupDTO;
import com.codeWithProjects.fitnessTrackerServer.dto.CommentDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.*;
import com.codeWithProjects.fitnessTrackerServer.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityGroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public CommunityGroupDTO createGroup(CommunityGroupDTO dto) {
        CommunityGroup group = new CommunityGroup();
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        return toDto(groupRepository.save(group));
    }

    @Transactional
    public void joinGroup(Long groupId, Long userId) {
        CommunityGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        group.getMembers().add(user);
        groupRepository.save(group);
    }

    @Transactional
    public CommentDTO addComment(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        
        if (dto.getProgramId() != null) {
            comment.setProgram(programRepository.findById(dto.getProgramId())
                    .orElseThrow(() -> new EntityNotFoundException("Program not found")));
        }
        if (dto.getGroupId() != null) {
            comment.setGroup(groupRepository.findById(dto.getGroupId())
                    .orElseThrow(() -> new EntityNotFoundException("Group not found")));
        }
        
        return toDto(commentRepository.save(comment));
    }

    private CommunityGroupDTO toDto(CommunityGroup group) {
        CommunityGroupDTO dto = new CommunityGroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setMemberIds(group.getMembers().stream()
                .map(User::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    private CommentDTO toDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUserId(comment.getUser().getId());
        dto.setProgramId(comment.getProgram() != null ? comment.getProgram().getId() : null);
        dto.setGroupId(comment.getGroup() != null ? comment.getGroup().getId() : null);
        return dto;
    }
}