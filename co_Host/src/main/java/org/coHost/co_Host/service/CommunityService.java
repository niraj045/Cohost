package org.coHost.co_Host.service;

import org.coHost.co_Host.model.Community;
import org.coHost.co_Host.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Community createCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Community getCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found with id " + id));
    }
}
