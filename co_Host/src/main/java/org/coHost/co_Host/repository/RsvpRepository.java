package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
    // Add custom query methods if needed
}
