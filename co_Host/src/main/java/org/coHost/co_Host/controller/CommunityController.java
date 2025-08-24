package org.coHost.co_Host.controller;

import java.security.Principal;
import java.util.List;

import org.coHost.co_Host.model.Community;
import org.coHost.co_Host.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }

    @PostMapping
    public ResponseEntity<Community> createCommunity(@RequestBody Community community, Principal principal) {
        // Get the logged-in user's name and pass it to the service
        Community createdCommunity = communityService.createCommunity(community, principal.getName());
        return ResponseEntity.ok(createdCommunity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunity(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getCommunityById(id));
    }
}
