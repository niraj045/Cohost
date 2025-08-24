package org.coHost.co_Host.service;

import java.util.List;

import org.coHost.co_Host.model.Community;
import org.coHost.co_Host.model.User;
import org.coHost.co_Host.repository.CommunityRepository;
import org.coHost.co_Host.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    
    public CommunityService(CommunityRepository communityRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Community createCommunity(Community community, String creatorUsername) {
        User creator = userRepository.findByUsername(creatorUsername);
        if (creator == null) {
            throw new RuntimeException("Creator not found with username: " + creatorUsername);
        }
        community.setCreator(creator);
        return communityRepository.save(community);
    }

    public Community getCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found with id " + id));
    }
}
