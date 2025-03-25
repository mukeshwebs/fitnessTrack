package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.CommunityGroupDTO;
import com.codeWithProjects.fitnessTrackerServer.dto.CommentDTO;
import com.codeWithProjects.fitnessTrackerServer.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/groups")
    public ResponseEntity<CommunityGroupDTO> createGroup(@Valid @RequestBody CommunityGroupDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(communityService.createGroup(dto));
    }

    @PostMapping("/groups/{groupId}/join")
    public ResponseEntity<Void> joinGroup(@PathVariable Long groupId, @RequestParam Long userId) {
        communityService.joinGroup(groupId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(communityService.addComment(dto));
    }
}