package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // Add custom query methods if needed
}
